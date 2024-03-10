package com.crms.model.enumeration;

/**
 * Created by doductrung
 */
public enum ConditionType {

  AND,
  OR;

  public static ConditionType get(String code) {
    for (ConditionType template : values()) {
      if (template.name().equals(code)) {
        return template;
      }
    }
    return null;
  }
}
