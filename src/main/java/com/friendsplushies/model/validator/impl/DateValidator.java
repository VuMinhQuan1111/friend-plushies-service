package com.friendsplushies.model.validator.impl;

import com.friendsplushies.model.validator.Date;
import java.text.ParseException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.time.DateUtils;

/**
 * Created by chautn on 8/14/2017.
 */
public class DateValidator implements ConstraintValidator<Date, String> {

  @Override
  public void initialize(Date isDate) {
  }

  @Override
  public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {
    if (date == null) {
      return true;
    }
    try {
      DateUtils.parseDate(date, "dd/MM/yyyy", "dd-MM-yyyy");
      return true;
    } catch (ParseException e) {
      return false;
    }
  }
}
