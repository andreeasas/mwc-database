/*
 * Copyright (c) 2018 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.mwc.model.internationalization;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author <a href="mailto:asas@ssi-schaefer-noell.com">asas</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

@Entity
@Table(name = "UM")
@NamedQueries({ //
  @NamedQuery(name = MonetaryUnit.UM_BY_ID, query = "from MonetaryUnit where code = :code") //
})
public class MonetaryUnit implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String UM_BY_ID = "User.by.id";

  @Id
  private String code;

  @OneToMany( //
  mappedBy = "um", //
  cascade = { CascadeType.ALL } //
  )
  private Collection<UMDescription> descriptions;

  public MonetaryUnit() {
  }

  public MonetaryUnit(String code) {
    super();
    this.code = code;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Collection<UMDescription> getDescriptions() {
    return descriptions;
  }

  public void setDescriptions(Collection<UMDescription> descriptions) {
    this.descriptions = descriptions;
  }

}
