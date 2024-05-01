package com.friendsplushies.model.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author doductrung
 */
@Getter
@Setter
public class UserPermissionSearchRequest extends SearchRequest {

  private Long userId;
  private String username;
  private String phone;
  private String email;
  private String name;
  private String type;
  private String[] types;
  private String permission;
  private Long fromCreatedDate;
  private Long toCreatedDate;
}
