package com.crms.constant.type;

/**
 * Author: chautn on 4/25/2019 11:06 AM
 */
public enum DeviceType {
  BROWSER,
  ANDROID,
  IOS,
  UNKNOWN;

  public static DeviceType type(String type) {
    for (DeviceType object : values()) {
      if (object.name().equals(type)) {
        return object;
      }
    }
    return UNKNOWN;
  }
}
