package com.friendsplushies.constant.type;

/**
 * @author vuld
 */
public enum UserGroup {
  PERSONAL,
  GROUP;

  public static UserGroup type(String type) {
    for (UserGroup object : values()) {
      if (object.name().equals(type)) {
        return object;
      }
    }
    return null;
  }

}
