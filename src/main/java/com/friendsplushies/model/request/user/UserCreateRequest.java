package com.friendsplushies.model.request.user;

import com.friendsplushies.constant.type.ObjectStatus;
import com.friendsplushies.constant.type.UserType;
import com.friendsplushies.model.validator.FieldsNotNullOrEmptyBlank;
import com.friendsplushies.model.validator.FieldsNotNullOrEmptyBlank.List;
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
//    ObjectStatus status = ObjectStatus.status(this.getStatus());
//    UserType type = UserType.userType(this.getUserType());
    this.setPassword(DigestUtils.md5DigestAsHex(this.password.getBytes()));
//    this.setUserType(type.name());
//    this.setStatus(status.name());
    return super.preRequest();
  }
}
