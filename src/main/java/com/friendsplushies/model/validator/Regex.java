package com.friendsplushies.model.validator;

import com.friendsplushies.model.validator.impl.RegexValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Author: chautn on 4/12/2019 12:29 PM
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RegexValidator.class)
public @interface Regex {

  String message() default "{field} is inputted in wrong format";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String regex() default "";

  String field();
}
