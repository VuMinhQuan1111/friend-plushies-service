package com.friendsplushies.model.validator.impl;

import com.friendsplushies.model.validator.FieldsNotNullOrEmptyBlank;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanWrapperImpl;

/**
 * Author: chautn on 4/12/2019 2:23 PM
 */
public class FieldsNotNullOrEmptyBlankValidator implements ConstraintValidator<FieldsNotNullOrEmptyBlank, Object> {

  private String field;

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    if (value == null) {
      return false;
    } else {
      Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
      return fieldValue != null && (!(fieldValue instanceof String) || !StringUtils.isBlank((String) fieldValue));
    }
  }

  @Override
  public void initialize(FieldsNotNullOrEmptyBlank constraintAnnotation) {
    this.field = constraintAnnotation.field();
  }
}
