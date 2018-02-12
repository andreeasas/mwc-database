/*
 * Copyright (c) 2018 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.mwc.common.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author <a href="mailto:asas@ssi-schaefer-noell.com">asas</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class Utils {

  private static final String STRING_ENCODING = "UTF-8";
  private static final String KEY = "MONEYWITHCARE_PS"; // 16 chars needed
  private static final String ALGORITHM = "AES";
  private static final Key AES_KEY = new SecretKeySpec(KEY.getBytes(), ALGORITHM);

  public static String getParentsFromServletPath(String path) {
    {
      int count = 0;
      for (int i = 0; i < path.length(); i++) {
        if (path.charAt(i) == '/') {
          count++;
        }
      }

      String result = "";
      for (int i = 1; i < count; i++) {
        result += "../";
      }
      return result;
    }
  }

  private static Cipher getCipher(int mode) throws Exception {
    Cipher cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(mode, AES_KEY);
    return cipher;
  }

  private static String toHexString(byte b[]) {
    StringBuffer hexString = new StringBuffer();
    for (int i = 0; i < b.length; i++) {
      String plainText = Integer.toHexString(0xff & b[i]);
      if (plainText.length() < 2) {
        plainText = "0" + plainText;
      }
      hexString.append(plainText);
    }
    return hexString.toString();
  }

  public static String encrypt(String input) {
    try {
      Cipher cipher = getCipher(Cipher.ENCRYPT_MODE);
      return toHexString(cipher.doFinal(input.getBytes(STRING_ENCODING)));
    } catch (Exception ex) {
      throw new RuntimeException();
    }
  }

}
