package com.friendsplushies.constant.type;

/**
 * Author: chautn on 4/10/2019 2:18 PM
 */
public enum ObjectStatus {
  ACTIVE,
  INACTIVE,
  LOCKED,
  DISABLED,
  VALIDATED;

  public static ObjectStatus status(String status) {
    for (ObjectStatus objectStatus : values()) {
      if (objectStatus.name().equals(status)) {
        return objectStatus;
      }
    }
    return INACTIVE;
  }
}
