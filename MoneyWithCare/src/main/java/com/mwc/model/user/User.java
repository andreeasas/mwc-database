/*
 * Copyright (c) 2018 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.mwc.model.user;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.mwc.common.util.Utils;
import com.mwc.model.expense.Category;

/**
 * @author <a href="mailto:asas@ssi-schaefer-noell.com">asas</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

@Entity
@Table(name = "DBUSER")
@NamedQueries({ //
  @NamedQuery(name = User.USER_BY_NAME, query = "from User where user = :user"), //
  @NamedQuery(name = User.USER_BY_NAME_AND_PASS, query = "from User where user = :user and password = :pass"), //
  @NamedQuery(name = User.USER_ALL, query = "from User") //
})
public class User implements Serializable, Comparable<User> {
  private static final long serialVersionUID = 1L;

  public static final String USER_BY_NAME = "User.by.name";
  public static final String USER_BY_NAME_AND_PASS = "User.by.name.and.pass";
  public static final String USER_ALL = "User.all";

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
  @SequenceGenerator(initialValue = 1, sequenceName = "seq_gen_users", name = "gen")
  private long id;
  private String username;
  private String password;
  private String name;

  @OneToMany( //
  mappedBy = "dbUser", //
  cascade = { CascadeType.ALL } //
  )
  private List<Member> members;

  @OneToMany( //
  mappedBy = "user", //
  cascade = { CascadeType.ALL } //
  )
  private List<Category> categories;

  public User() {
  }

  public User(String username, String password, String name) {
    super();
    this.username = username;
    this.password = Utils.encrypt(password);
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Member> getMembers() {
    return members;
  }

  public void setMembers(List<Member> members) {
    this.members = members;
  }

  public List<Category> getCategories() {
    return categories;
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((username == null) ? 0 : username.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    User other = (User) obj;
    if (id != other.id)
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (username == null) {
      if (other.username != null)
        return false;
    } else if (!username.equals(other.username))
      return false;
    return true;
  }

  @Override
  public int compareTo(User arg0) {
    // TODO Auto-generated method stub
    return 0;
  }

}
