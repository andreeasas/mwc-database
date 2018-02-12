/*
 * Copyright (c) 2018 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.mwc.common.util;

import com.mwc.model.internationalization.MonetaryUnit;

/**
 * @author <a href="mailto:asas@ssi-schaefer-noell.com">asas</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class OracleDatabase implements Database {

  private static OracleDatabase instance;

  private OracleDatabase() {
  }

  public static OracleDatabase getInstance() {
    if (instance == null) {
      instance = new OracleDatabase();
    }
    return instance;
  }

  @Override
  public boolean checkUserLogin(String user, String password) {

    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public long convertCurrencyToReference(long value, MonetaryUnit current, MonetaryUnit reference) {
    // TODO Auto-generated method stub
    return 0;
  }

}
