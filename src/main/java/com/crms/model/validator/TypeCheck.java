package com.crms.model.validator;

import com.crms.model.validator.impl.TypeCheckValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author vuld on 4/11/2019.
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TypeCheckValidator.class)
public @interface TypeCheck {

  String message() default "Invalid enum {value}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  Class<?> value();

}
