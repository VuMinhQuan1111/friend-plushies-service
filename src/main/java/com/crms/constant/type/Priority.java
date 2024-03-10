package com.crms.constant.type;

/**
 * @author vuld
 */
public enum Priority {
  ONE,
  TWO,
  THREE;

  public static Priority type(String type) {
    for (Priority object : values()) {
      if (object.name().equals(type)) {
        return object;
      }
    }
    return null;
  }

}
