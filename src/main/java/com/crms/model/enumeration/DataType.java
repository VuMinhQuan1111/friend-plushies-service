package com.crms.model.enumeration;

/**
 * Created by doductrung
 */
public enum DataType {

  STRING,
  LONG,
  DOUBLE,
  DATE,
  TIMESTAMP;

  public static DataType get(String code) {
    for (DataType template : values()) {
      if (template.name().equals(code)) {
        return template;
      }
    }
    return null;
  }
}
