package com.friendsplushies.model.request.user;

import com.friendsplushies.model.validator.FieldsNotNullOrEmptyBlank;
import com.friendsplushies.model.validator.FieldsNotNullOrEmptyBlank.List;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

/**
 * Author: chautn on 4/23/2019 12:24 PM
 */
@Getter
@Setter
@List({
    @FieldsNotNullOrEmptyBlank(field = "username")
})
public class UserUpdatePasswordRequest extends UserRequest {

  private String otp;
  private String token;
  private String oldPassword;

  @Override
  public UserRequest preRequest() {
    this.setPassword(DigestUtils.md5DigestAsHex(this.password.getBytes()));
    if (StringUtils.isNotBlank(oldPassword)) {
      this.setOldPassword(DigestUtils.md5DigestAsHex(this.oldPassword.getBytes()));
    }
    return super.preRequest();
  }
}
