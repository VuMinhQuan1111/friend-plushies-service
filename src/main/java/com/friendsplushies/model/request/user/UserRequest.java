package com.friendsplushies.model.request.user;

import com.friendsplushies.constant.type.ObjectStatus;
import com.friendsplushies.model.validator.Regex;
import com.friendsplushies.model.validator.TypeCheck;
import com.friendsplushies.model.validator.impl.RegexValidator;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * Author: chautn on 4/9/2019 3:17 PM
 */
@Getter
@Setter
public class UserRequest {

  private Long userId;

//  @Size(min = 6, max = 100)
  protected String username;

  @Size(min = 8)
  protected String password;

  @Regex(regex = RegexValidator.EMAIL, field = "email")
  protected String email;

//  @Regex(regex = RegexValidator.NAME)
  protected String name;

//  @Regex(regex = RegexValidator.PHONE)
  private String phone;

  private String address;

  public UserRequest preRequest() {
    if (StringUtils.isNotBlank(this.getName())) {
      this.setName(this.getName().trim());
    }
    if (StringUtils.isNotBlank(this.getEmail())) {
      this.setEmail(this.getEmail().trim().toLowerCase());
    }
    return this;
  }

}
