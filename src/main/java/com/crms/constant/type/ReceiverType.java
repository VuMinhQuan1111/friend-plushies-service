package com.crms.constant.type;

/**
 * @author vuld
 */
public enum ReceiverType {
  NATURAL_DISASTER,
  DISABILITIES_ORANGE_VICTIM,
  SERIOUS_DISEASES,
  ACCIDENT,
  ORPHAN_ELDERLY,
  DIFFICULT_CIRCUMSTANCES,
  HOSPITAL,
  SOCIAL_PROTECTION,
  SCHOOL,
  PUBLIC;

  public static ReceiverType type(String type) {
    for (ReceiverType object : values()) {
      if (object.name().equals(type)) {
        return object;
      }
    }
    return null;
  }

}
