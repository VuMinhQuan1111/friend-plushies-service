package com.friendsplushies.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Author: chautn on 4/14/2019 9:09 PM
 */
@Getter
@Setter
@EqualsAndHashCode
public class PermissionResponse {

  private String permissionName;
  private String description;

  @JsonIgnore
  @EqualsAndHashCode.Exclude
  private Boolean accessApi;
}
