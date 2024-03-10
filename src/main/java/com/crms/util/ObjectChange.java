package com.crms.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author tuadCS
 * @date 12/6/2023
 */

@Getter
@Setter
@AllArgsConstructor
public class ObjectChange {

  private String fieldName;
  private Object oldValue;
  private Object newValue;
}
