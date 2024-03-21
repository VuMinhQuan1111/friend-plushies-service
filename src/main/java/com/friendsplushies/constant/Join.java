package com.friendsplushies.constant;

/**
 * @author vuld
 */
public enum Join {
  LJF(" LEFT JOIN FETCH "),
  IJF(" INNER JOIN FETCH "),
  LJ(" LEFT JOIN "),
  IJ(" INNER JOIN ");

  Join(String value) {
    this.value = value;
  }

  private String value;

  public String value() {
    return value;
  }

  public static Join type(String type) {
    for (Join object : values()) {
      if (object.name().equals(type)) {
        return object;
      }
    }
    return null;
  }

  public static Join value(String value) {
    for (Join object : values()) {
      if (object.value().trim().equals(value)) {
        return object;
      }
    }
    return null;
  }

}
