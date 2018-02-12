/*
 * Copyright (c) 2018 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.mwc.model.expense;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.mwc.model.internationalization.MonetaryUnit;
import com.mwc.model.user.Member;
import com.mwc.model.user.User;

/**
 * @author <a href="mailto:asas@ssi-schaefer-noell.com">asas</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

@Entity
public class Cost {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cost_gen")
  @SequenceGenerator(initialValue = 1, sequenceName = "seq_gen_costs", name = "cost_gen")
  private long id;

  @ManyToOne
  private User user;

  @ManyToOne
  private Member member;

  @ManyToOne
  private Category category;

  @ManyToOne
  private MonetaryUnit um;

  private Double value;

  @Basic
  private LocalDate costDate;

  private String description;

  public Cost() {
  }

  public Cost(MonetaryUnit um, Double value, Date date, String description) {
    super();
    this.um = um;
    this.value = value;
    //    this.date = date;
    this.description = description;
  }

  public Cost(User user, Member member, Category category, MonetaryUnit um, Double value, LocalDate costDate, String description) {
    super();
    this.user = user;
    this.member = member;
    this.category = category;
    this.um = um;
    this.value = value;
    this.costDate = costDate;
    this.description = description;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Member getMember() {
    return member;
  }

  public void setMember(Member member) {
    this.member = member;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public MonetaryUnit getUm() {
    return um;
  }

  public void setUm(MonetaryUnit um) {
    this.um = um;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public LocalDate getCostDate() {
    return costDate;
  }

  public void setCostDate(LocalDate costDate) {
    this.costDate = costDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
