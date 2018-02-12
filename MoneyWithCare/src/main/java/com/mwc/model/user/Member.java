/*
 * Copyright (c) 2018 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.mwc.model.user;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.mwc.model.expense.Category;

/**
 * @author <a href="mailto:asas@ssi-schaefer-noell.com">asas</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

@Entity
@NamedQueries({ //
  @NamedQuery(name = Member.MEMBER_BY_USER, query = "from Member where user = :user"), //
  @NamedQuery(name = Member.MEMBER_BY_USER_AND_NAME, query = "from Member where user = :user and name = :name") //
})
public class Member implements Serializable, Comparable<Member> {
  private static final long serialVersionUID = 1L;

  public static final String MEMBER_BY_USER = "Member.by.user";
  public static final String MEMBER_BY_USER_AND_NAME = "Member.by.user.and.name";

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_gen")
  @SequenceGenerator(initialValue = 1, sequenceName = "seq_gen_members", name = "member_gen")
  private long id;
  private String name;

  @ManyToOne
  @JoinColumn(nullable = false)
  private User dbUser;

  @OneToMany(mappedBy = "member")
  private List<Category> categories;

  public Member() {
    super();
  }

  public Member(String name) {
    this.name = name;
  }

  public Member(String name, User dbUser) {
    this(name);
    this.dbUser = dbUser;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public User getDbUser() {
    return dbUser;
  }

  public void setDbUser(User dbUser) {
    this.dbUser = dbUser;
  }

  public List<Category> getCategories() {
    return categories;
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }

  @Override
  public int compareTo(Member user1) {
    // TODO Auto-generated method stub
    return 0;
  }

}
