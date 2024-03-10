package com.crms.constant.type;

/**
 * @author vuld
 */
public enum ReceiverSupportStatus {
  WAITING,
  INPROGRESS,
  FINISHED,
  UNKNOWN;

  public static ReceiverSupportStatus type(String type) {
    for (ReceiverSupportStatus object : values()) {
      if (object.name().equals(type)) {
        return object;
      }
    }
    return null;
  }

}
