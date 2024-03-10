package com.crms.constant.type;

/**
 * @author vuld
 */
public enum ReceiverStatus {
  PROPOSED,
  COORDINATOR_APPROVED,
  RED_CROSS_APPROVED,
  REJECTED,
  INACTIVE;

  public static ReceiverStatus type(String type) {
    for (ReceiverStatus object : values()) {
      if (object.name().equals(type)) {
        return object;
      }
    }
    return null;
  }

}
