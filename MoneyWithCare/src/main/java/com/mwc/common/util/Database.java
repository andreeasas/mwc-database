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

public interface Database {

  public boolean checkUserLogin(String user, String password);

  public long convertCurrencyToReference(long value, MonetaryUnit current, MonetaryUnit reference);

}
