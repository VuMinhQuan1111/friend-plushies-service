package com.crms.model.request.user;

import com.crms.constant.type.ResetPasswordMethod;
import com.crms.model.validator.FieldsNotNullOrEmptyBlank;
import com.crms.model.validator.FieldsNotNullOrEmptyBlank.List;
import com.crms.model.validator.TypeCheck;
import lombok.Getter;
import lombok.Setter;

/**
 * Author: chautn on 4/23/2019 3:50 PM
 */
@Getter
@Setter
@List({
    @FieldsNotNullOrEmptyBlank(field = "method")
})
public class UserResetPasswordRequest extends UserRequest {

  @TypeCheck(ResetPasswordMethod.class)
  private String method;
}
