package com.friendsplushies.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
//import com.crms.model.response.acl.AclActionResponse;
//import com.crms.model.response.acl.AclGroupResponse;
//import com.crms.model.response.acl.AclPermissionResponse;

import lombok.Getter;
import lombok.Setter;

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
  private String title;
  private String facebookId;
  private String googleId;
  private Integer loginCount;
  private String gender;
  private String status;
  private String createdBy;
  private Long createdDate;
  private String firstName;
  private String lastName;
  //  private String linkedin;
//  private String facebook;
//  private String twitter;
//  private String youtube;
//  private String whatsapp;
  private String mobile;
  private String position;
//  private List<PermissionResponse> permissions;
//
//  private List<AclGroupResponse> aclGroups;
//  private List<AclPermissionResponse> aclPermissions;
//  private List<AclActionResponse> aclActions;
//  private Long dailyGoal;

}
