package com.crms.model.validator;

import com.crms.model.validator.impl.FieldsNotNullOrEmptyBlankValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Author: chautn on 4/12/2019 2:25 PM
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FieldsNotNullOrEmptyBlankValidator.class})
public @interface FieldsNotNullOrEmptyBlank {

  String message() default "{field} is required";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String field();

  @Target({ElementType.TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @interface List {
    FieldsNotNullOrEmptyBlank[] value();
  }
}
