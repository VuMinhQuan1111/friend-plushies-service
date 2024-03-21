package com.friendsplushies.model.validator;

import com.friendsplushies.model.validator.impl.DateValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Created by chautn on 8/14/2017.
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {DateValidator.class})
public @interface Date {

  String message() default "Invalid date format";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
