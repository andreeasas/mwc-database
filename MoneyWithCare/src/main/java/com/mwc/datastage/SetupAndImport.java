/*
 * Copyright (c) 2018 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.mwc.datastage;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;

import com.mwc.common.consts.CategoryConsts;
import com.mwc.common.consts.HouseConsts;
import com.mwc.common.util.WithSessionAndTransaction;
import com.mwc.model.expense.Category;
import com.mwc.model.expense.Cost;
import com.mwc.model.internationalization.Language;
import com.mwc.model.internationalization.MonetaryUnit;
import com.mwc.model.internationalization.UMDescription;
import com.mwc.model.user.Member;
import com.mwc.model.user.User;

/**
 * @author <a href="mailto:asas@ssi-schaefer-noell.com">asas</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class SetupAndImport {

  public static void main(String[] args) {

    WithSessionAndTransaction<Object> insertData = new WithSessionAndTransaction<Object>() {

      @Override
      protected void executeBusinessLogic(Session session) {

        /** ---CREATE USERS--- */
        User admin = createUser("admin", "boss", "admin", null);
        Member minion = new Member("minion");
        User asa = createUser("asa", "demo", "sas group", Arrays.asList(new Member("andreea"), minion));

        /** ---CREATE LANGUAGES--- */
        Language langRo = new Language("rum");
        Language langEn = new Language("eng");
        Language langGe = new Language("ger");

        /** ---CREATE MONETARY UNITS--- */
        MonetaryUnit ron = createUM("RON", Arrays.asList(new UMDescription(langRo, "leul romanesc"), new UMDescription(langEn, "Romanian valute")));
        MonetaryUnit eur = createUM("EUR", Arrays.asList(new UMDescription(langRo, "euro"), new UMDescription(langEn, "euro")));

        /** ---CREATE DEFAULT CATEGORIES--- */
        String[] categoriesName = new String[] { //
          CategoryConsts.COMMUNICATION, //
          CategoryConsts.DEBT, //
          CategoryConsts.EDUCATION, //
          CategoryConsts.FOOD, //
          CategoryConsts.HOUSE, //
          CategoryConsts.PERSONAL, //
          CategoryConsts.RECREATION, //
          CategoryConsts.TRANSPORT //
        };
        List<Category> categories = createCategoriesForUser(admin, categoriesName);

        /** ---CREATE DEFAULT SUBCATEGORIES--- */

        /** ---CREATE HOUSE'S SUBCATEGORIES--- */
        Category house = findCategory(CategoryConsts.HOUSE, categories);
        String[] houseSubcategories = new String[] { //
          HouseConsts.BILLS, //
          HouseConsts.EQUIPMENT, //
          HouseConsts.MAINTENANCE //
        };
        // automatically saven when saving the categories
        List<Category> houseCategories = createSubCategories(house, houseSubcategories);

        /** ---CREATE EXAMPLE COSTS--- */
        Category bill = findCategory(HouseConsts.BILLS, houseCategories);
        LocalDate dateIan = LocalDate.of(2018, Month.JANUARY, 23);
        Cost enelBill = new Cost(asa, null, bill, ron, new Double(45.70), dateIan, "facura Enel");

        Category equipment = findCategory(HouseConsts.EQUIPMENT, houseCategories);
        LocalDate dateFeb = LocalDate.of(2018, Month.FEBRUARY, 8);
        Cost gasCooker = new Cost(null, minion, equipment, ron, new Double(740.50), dateFeb, "aragaz");

        /** ---SAVE ENTITIES--- */
        // users also save the associated members and categories
        saveEntities(session, Arrays.asList(admin, asa));

        saveEntities(session, Arrays.asList(langRo, langEn, langGe));
        saveEntities(session, Arrays.asList(ron, eur));

        saveEntities(session, Arrays.asList(enelBill, gasCooker));

      }

      private void saveEntities(Session session, List<Object> entities) {
        entities.forEach(entity -> {
          session.save(entity);
        });
      }

    };

    insertData.run();

  }

  private static Category findCategory(String categName, List<Category> categories) {
    for (Category category : categories) {
      if (category.getName().equals(categName)) {
        return category;
      }
    }
    return null;
  }

  /**
   * @param user
   * @param subcategoriesName
   * @return 
   */
  private static List<Category> createCategoriesForUser(User user, String[] subcategoriesName) {
    List<Category> categories = new ArrayList<Category>();
    for (String categName : subcategoriesName) {
      categories.add(new Category(categName, null, user, null));
    }
    // categories will be automatically saved when saving the user
    if (user != null) {
      user.setCategories(categories);
    }
    return categories;
  }

  private static List<Category> createSubCategories(Category parent, String[] categoriesName) {
    List<Category> subcategories = new ArrayList<Category>();
    for (String categName : categoriesName) {
      subcategories.add(new Category(categName, parent, parent.getUser(), null));
    }
    // subcategories will be automatically saved when saving the categories
    if (parent != null) {
      parent.setSubcategories(subcategories);
    }
    return subcategories;
  }

  protected static List<Category> createDefaultCategories(User user, String[] categNames) {
    List<Category> categories = new ArrayList<Category>();
    for (String categName : categNames) {
      categories.add(new Category(categName, null, user, null));
    }
    return categories;
  }

  private static MonetaryUnit createUM(String code, List<UMDescription> descriptions) {
    MonetaryUnit um = new MonetaryUnit(code);
    if (descriptions == null) {
      return um;
    }

    // set connection in DB between um and descriptions
    descriptions.forEach(desc -> {
      desc.setUm(um);
    });

    // to save descriptions automatically when saving um
    um.setDescriptions(descriptions);
    return um;
  }

  private static User createUser(String username, String password, String name, List<Member> members) {
    User user = new User(username, password, name);
    if (members == null) {
      return user;
    }

    // for setting connction between user and members. 
    // Member = owner, so connection is only set by member.setUser(..); user.setMembers(..) won't set fk in db. 
    members.forEach(member -> {
      member.setDbUser(user);
    });

    // for saving the members automatically when saving the user (through cascade)
    user.setMembers(members);
    return user;
  }

}
