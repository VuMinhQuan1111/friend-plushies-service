package com.crms.model.request.user;

import com.crms.constant.type.ObjectStatus;
import com.crms.constant.type.UserType;
import com.crms.model.validator.FieldsNotNullOrEmptyBlank;
import com.crms.model.validator.FieldsNotNullOrEmptyBlank.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.DigestUtils;

/**
 * @author vuld
 */
@Getter
@Setter
@List({
    @FieldsNotNullOrEmptyBlank(field = "password"),
//    @FieldsNotNullOrEmptyBlank(field = "name"),
    @FieldsNotNullOrEmptyBlank(field = "email")
})
public class UserCreateRequest extends UserRequest {

//  private java.util.List<Long> aclGroupIds;
//  private java.util.List<Long> aclPermissionIds;

  @Override
  public UserRequest preRequest() {
    ObjectStatus status = ObjectStatus.status(this.getStatus());
    UserType type = UserType.userType(this.getType());
    this.setPassword(DigestUtils.md5DigestAsHex(this.password.getBytes()));
    this.setType(type.name());
    this.setStatus(status.name());
    return super.preRequest();
  }
}