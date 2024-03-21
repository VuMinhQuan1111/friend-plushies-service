package com.friendsplushies.constant;

/**
 * @author vuld
 */
public enum TableAlias {
  OBJECT("o");

  TableAlias(String alias) {
    this.alias = alias;
  }

  private String alias;

  public String alias() {
    return alias;
  }

  public static TableAlias type(String type) {
    for (TableAlias object : values()) {
      if (object.name().equals(type)) {
        return object;
      }
    }
    return null;
  }

}
