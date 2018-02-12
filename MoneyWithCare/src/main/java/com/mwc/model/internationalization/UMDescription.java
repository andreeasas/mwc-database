/*
 * Copyright (c) 2018 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.mwc.model.internationalization;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author <a href="mailto:asas@ssi-schaefer-noell.com">asas</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

@Entity
@NamedQueries({ //
  @NamedQuery(name = UMDescription.DESC_BY_UM, query = "from UMDescription where um = :um"), //
  @NamedQuery(name = UMDescription.DESC_BY_UM_AND_LANG, query = "from UMDescription where um = :um and lang = :lang") //
})
public class UMDescription implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String DESC_BY_UM = "Desription.by.um";
  public static final String DESC_BY_UM_AND_LANG = "Desription.by.um.and.language";

  @Id
  @ManyToOne
  private MonetaryUnit um;

  @Id
  @ManyToOne
  private Language lang;

  private String description;

  public UMDescription() {
  }

  public UMDescription(Language lang, String description) {
    super();
    this.lang = lang;
    this.description = description;
  }

  public UMDescription(MonetaryUnit um, Language lang, String description) {
    this(lang, description);
    this.um = um;
  }

  public MonetaryUnit getUm() {
    return um;
  }

  public void setUm(MonetaryUnit um) {
    this.um = um;
  }

  public Language getLang() {
    return lang;
  }

  public void setLang(Language lang) {
    this.lang = lang;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
