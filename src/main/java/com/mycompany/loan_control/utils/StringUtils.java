package com.mycompany.loan_control.utils;

public class StringUtils {

  public static String decapitalize(String str) {
      if (str == null || str.isEmpty()) {
          return str;
      }

      return new String(str.toLowerCase());
  }
}
