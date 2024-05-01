package com.friendsplushies.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author doductrung
 */
@Getter
@Setter
public class UserPermissionGrantRequest {

  private Long userId;
  private List<String> permissions;
}
