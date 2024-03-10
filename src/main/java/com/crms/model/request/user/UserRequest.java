package com.crms.model.request.user;

import com.crms.constant.type.ObjectStatus;
import com.crms.model.validator.Regex;
import com.crms.model.validator.TypeCheck;
import com.crms.model.validator.impl.RegexValidator;
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

//  @TypeCheck(value = UserType.class)
  private String type;

  @TypeCheck(value = ObjectStatus.class)
  private String status;

//  @Regex(regex = RegexValidator.PHONE)
  private String phone;

  private String address;
  private String title;
  private String facebookId;
  private String googleId;
  private String gender;
  private String firstName;
  private String lastName;
//  private String linkedin;
//  private String facebook;
//  private String twitter;
//  private String youtube;
//  private String whatsapp;
  private String mobile;
  private String position;
//  private Long officeId;
//  private Long dailyGoal;

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
