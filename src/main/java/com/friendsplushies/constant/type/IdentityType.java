package com.friendsplushies.constant.type;

/**
 * @author vuld
 */
public enum IdentityType {
  CMT,
  CAN_CUOC,
  HO_CHIEU;

  public static IdentityType type(String type) {
    for (IdentityType object : values()) {
      if (object.name().equals(type)) {
        return object;
      }
    }
    return null;
  }

}
