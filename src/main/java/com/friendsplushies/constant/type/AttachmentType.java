package com.friendsplushies.constant.type;

/**
 * @author vuld
 */
public enum AttachmentType {
  CITY,
  DISTRICT,
  PROSPECT,
  BUYER,
  SOS_ANSWER,
  USER;

  public static AttachmentType type(String type) {
    for (AttachmentType object : values()) {
      if (object.name().equals(type)) {
        return object;
      }
    }
    return null;
  }

}
