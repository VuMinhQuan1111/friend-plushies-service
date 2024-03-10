package com.crms.constant;

/**
 * @author vuld
 */
public enum Operator {
  EQUAL(" = "),
  LIKE(" like "),
  HIGHER(" > "),
  HIGHER_EQUAL(" >= "),
  LOWER(" < "),
  LOWER_EQUAL(" <= "),
  IN(" in "),
  IS(" is ");

  Operator(String value) {
    this.value = value;
  }

  private String value;

  public String value() {
    return value;
  }

  public static Operator type(String type) {
    for (Operator object : values()) {
      if (object.name().equals(type)) {
        return object;
      }
    }
    return null;
  }

  public static Operator value(String value) {
    for (Operator object : values()) {
      if (object.value().trim().equals(value)) {
        return object;
      }
    }
    return null;
  }

}
