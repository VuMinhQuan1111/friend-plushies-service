package com.friendsplushies.constant;

/**
 * @author vuld
 */
public enum Order {
  ASC(" asc "),
  DESC(" desc ");

  Order(String value) {
    this.value = value;
  }

  private String value;

  public String value() {
    return value;
  }

  public static Order type(String type) {
    for (Order object : values()) {
      if (object.name().equals(type)) {
        return object;
      }
    }
    return null;
  }

  public static Order value(String value) {
    for (Order object : values()) {
      if (object.value().trim().equals(value)) {
        return object;
      }
    }
    return null;
  }

}
