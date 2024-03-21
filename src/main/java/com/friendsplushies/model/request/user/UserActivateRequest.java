package com.friendsplushies.model.request.user;

import com.friendsplushies.constant.type.ObjectStatus;
import com.friendsplushies.model.validator.FieldsNotNullOrEmptyBlank;
import com.friendsplushies.model.validator.FieldsNotNullOrEmptyBlank.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by chautn on 5/11/2019
 */
@Getter
@Setter
@List({
    @FieldsNotNullOrEmptyBlank(field = "userId"),
    @FieldsNotNullOrEmptyBlank(field = "active")
})
public class UserActivateRequest extends UserUpdateRequest {

  private Boolean active;

  @Override
  public UserRequest preRequest() {
    setStatus(active != null && active ? ObjectStatus.ACTIVE.name() : ObjectStatus.INACTIVE.name());
    return super.preRequest();
  }
}
