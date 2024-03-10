package com.crms.util;

import com.crms.common.BadRequestException;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Author: chautn on 4/12/2019 3:25 PM
 */
public class ViolationExtractor<T> {

  private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

  private T object;

  public ViolationExtractor(T object) {
    this.object = object;
  }

  public void validate() {
    Set<ConstraintViolation<T>> violations = VALIDATOR.validate(object);
    if (CollectionUtils.isNotEmpty(violations)) {
      throw new BadRequestException(
          violations
              .stream()
              .map(v -> {
                String propertyPath = v.getPropertyPath().toString();
                return v.getMessage() + (StringUtils.isNotBlank(propertyPath) ? (" " + propertyPath) : "");
              })
              .collect(Collectors.toList())
              .toArray(new String[0])
      );
    }
  }
}
