package com.crms.constant.type;

/**
 * @author vuld
 */
public enum Frequency {
  ONE_TIME,
  PERMANENT;

  public static Frequency type(String type) {
    for (Frequency object : values()) {
      if (object.name().equals(type)) {
        return object;
      }
    }
    return null;
  }

}
