package com.crms.model.request.user;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

/**
 * @author vuld
 */
@Getter
@Setter
public class UserUpdateRequest extends UserRequest {

  @Override
  public UserRequest preRequest() {
    if(StringUtils.isNotEmpty(this.getPassword())){
      this.setPassword(DigestUtils.md5DigestAsHex(this.password.getBytes()));
      return super.preRequest();
    }
    return super.preRequest();
  }
}
