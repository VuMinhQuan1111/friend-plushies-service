package com.friendsplushies.model.request.user;

import com.friendsplushies.constant.type.ResetPasswordMethod;
import com.friendsplushies.model.validator.FieldsNotNullOrEmptyBlank;
import com.friendsplushies.model.validator.FieldsNotNullOrEmptyBlank.List;
import com.friendsplushies.model.validator.TypeCheck;
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
