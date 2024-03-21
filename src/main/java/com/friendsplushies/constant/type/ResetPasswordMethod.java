package com.friendsplushies.constant.type;

/**
 * Author: chautn on 4/23/2019 3:47 PM
 */
public enum  ResetPasswordMethod {
  OTP,
  EMAIL;

  public static ResetPasswordMethod method(String method) {
    for (ResetPasswordMethod object : values()) {
      if (object.name().equalsIgnoreCase(method)) {
        return object;
      }
    }
    return null;
  }
}
