/*
 * Copyright (c) 2018 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.mwc.common.util;

/**
 * @author <a href="mailto:asas@ssi-schaefer-noell.com">asas</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class ApplicationFactory {

  private static ApplicationFactory instance;

  private ApplicationFactory() {
  }

  public static ApplicationFactory getInstance() {
    if (instance == null) {
      instance = new ApplicationFactory();
    }
    return instance;
  }

  public Database getDatabase() {
    return OracleDatabase.getInstance();
  }

}
