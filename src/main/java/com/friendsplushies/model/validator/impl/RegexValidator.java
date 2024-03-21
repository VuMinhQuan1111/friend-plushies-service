package com.friendsplushies.model.validator.impl;

import com.friendsplushies.model.validator.Regex;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

/**
 * Author: chautn on 4/12/2019 12:30 PM
 */
public class RegexValidator implements ConstraintValidator<Regex, String> {

  public static final String EMAIL = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
  public static final String NAME = "^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,100}";
  public static final String PHONE = "\\d{8,15}";

  private String regex;
  private String field;

  @Override
  public void initialize(Regex constraintAnnotation) {
    this.regex = constraintAnnotation.regex();
    this.field = constraintAnnotation.field();
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return StringUtils.isEmpty(regex) || value == null || (Pattern.compile(regex).matcher(value).matches());
  }

}
