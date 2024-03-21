package com.friendsplushies.model.validator.impl;

import com.friendsplushies.model.validator.TypeCheck;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author vuld on 4/11/2019.
 */
public class TypeCheckValidator implements ConstraintValidator<TypeCheck, String> {

  private Class<?> classType;

  @Override
  public void initialize(TypeCheck typeCheck) {
    this.classType = typeCheck.value();
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    if (value == null) {
      return true;
    }
    try {
      Method name = classType.getMethod("name");
      Object[] types = classType.getEnumConstants();
      for (Object obj : types) {
        String type = (String) name.invoke(obj);
        if (type.equals(value)) {
          return true;
        }
      }
      return false;
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      return false;
    }
  }

}
