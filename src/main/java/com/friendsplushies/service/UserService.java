package com.friendsplushies.service;

import com.friendsplushies.config.oauth2.SOAuth2Request;
import com.friendsplushies.config.oauth2.SUserDetails;
import com.friendsplushies.model.entity.User;
import com.friendsplushies.model.request.user.*;
import com.friendsplushies.model.response.UserResponse;
//import com.crms.model.response.acl.AclActionResponse;
//import com.crms.model.response.acl.AclGroupResponse;
import com.friendsplushies.util.cruds.service.IService;
import java.lang.reflect.InvocationTargetException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.HttpRequestMethodNotSupportedException;

/**
 * @author vuld
 * @date 19/08/2020
 */
public interface UserService extends IService<UserRequest, UserResponse, User> {

  UserResponse register(UserCreateRequest userRequest) throws IllegalAccessException;

  OAuth2AccessToken login(SOAuth2Request request) throws HttpRequestMethodNotSupportedException;

  void logout();

  SUserDetails details();

  UserResponse me();

  UserResponse updateUser(UserUpdateRequest userUpdateRequest) throws IllegalAccessException;

  UserResponse setPassword(UserUpdatePasswordRequest request);

  void resetPassword(UserResetPasswordRequest method);

  UserResponse activate(UserActivateRequest request)
      throws IllegalAccessException, InvocationTargetException;

//  Boolean putAclGroup(Long userId, Long groupId, Boolean checked);

//  Boolean putAclPermission(Long userId, Long permissionId, Boolean checked);

//  List<AclGroupResponse> getAclGroups(Long userId);

  Boolean checkAccessUIComponent(String component);

//  List<AclActionResponse> getAllAccessUIComponent();
}
