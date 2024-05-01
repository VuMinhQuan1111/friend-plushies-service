package com.friendsplushies.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
//import com.crms.model.response.acl.AclActionResponse;
//import com.crms.model.response.acl.AclGroupResponse;
//import com.crms.model.response.acl.AclPermissionResponse;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Author: chautn on 4/9/2019 3:12 PM
 */
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class UserResponse {

  private Long userId;
  private String username;
  private String password;
  private String tempPassword;
  private String type;
  private String name;
  private String phone;
  private String email;
  private String address;
  private String userType;
  private String createdBy;
  private Long createdDate;
  private List<PermissionResponse> permissions;
//
//  private List<AclGroupResponse> aclGroups;
//  private List<AclPermissionResponse> aclPermissions;
//  private List<AclActionResponse> aclActions;

}
