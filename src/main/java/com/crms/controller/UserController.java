package com.crms.controller;

import com.crms.config.oauth2.SOAuth2Request;
import com.crms.constant.ServicePath;
import com.crms.model.entity.User;
import com.crms.model.request.user.UserActivateRequest;
import com.crms.model.request.user.UserCreateRequest;
import com.crms.model.request.user.UserRequest;
import com.crms.model.request.user.UserResetPasswordRequest;
import com.crms.model.request.user.UserUpdatePasswordRequest;
import com.crms.model.response.UserResponse;
import com.crms.service.UserService;
import com.crms.util.RestResult;
import com.crms.util.ViolationExtractor;
import com.crms.util.cruds.controller.AbstractController;
import io.swagger.annotations.ApiOperation;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vuld
 * @date 19/08/2020
 */
@RestController
@RequestMapping(ServicePath.USER)
public class UserController extends AbstractController<UserRequest, UserResponse, User> {

  public static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserService userService;

  public UserController() {
  }

  @Autowired
  public UserController(UserService userService) {
    super(userService);
  }

  @PostMapping(ServicePath.REGISTER)
  public ResponseEntity register(
      @RequestBody UserCreateRequest request
  ) throws IllegalAccessException {
    new ViolationExtractor<>(request).validate();
    request.preRequest();
    RestResult<UserResponse> result = new RestResult<>();
    result.setStatus(RestResult.STATUS_SUCCESS);
    result.addMessage("Dang ki thanh cong");
    result.setData(userService.register(request));
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @PostMapping(ServicePath.LOGIN)
  public ResponseEntity login(@RequestBody SOAuth2Request request) {
    try {
      RestResult<OAuth2AccessToken> result = new RestResult<>();
      result.setStatus(RestResult.STATUS_SUCCESS);
      result.addMessage("Dang nhap thanh cong");
      result.setData(userService.login(request));
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e) {
      throw new OAuth2Exception(e.getMessage(), e);
    }
  }

  @PostMapping(ServicePath.LOGOUT)
  public ResponseEntity logout() {
    try {
      userService.logout();
      RestResult<Void> result = new RestResult<>();
      result.setStatus(RestResult.STATUS_SUCCESS);
      result.addMessage("Dang xuat thanh cong");
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e) {
      throw new OAuth2Exception(e.getMessage(), e);
    }
  }

  @ApiOperation(value = "Update User Status", produces = MediaType.APPLICATION_JSON_VALUE)
  @RequestMapping(value = ServicePath.STATUS, method = RequestMethod.PUT)
  public ResponseEntity<RestResult<UserResponse>> activateUser(
      @RequestBody UserActivateRequest request
  ) throws IllegalAccessException, InvocationTargetException {
    new ViolationExtractor<>(request).validate();
    request.preRequest();
    RestResult<UserResponse> result = new RestResult<>();
    result.ok("Update user thanh cong");
    result.setData(userService.activate(request));
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @ApiOperation(value = "Request reset password", produces = MediaType.APPLICATION_JSON_VALUE)
  @PostMapping(value = ServicePath.PASSWORD + ServicePath.RESET)
  public ResponseEntity resetPassword(
      @RequestBody UserResetPasswordRequest request
  ) {
    new ViolationExtractor<>(request).validate();
    userService.resetPassword(request);
    RestResult<String> result = new RestResult<>();
    result.ok("Yeu cau dat lai mat khau thanh cong");
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @ApiOperation(value = "Set new password", produces = MediaType.APPLICATION_JSON_VALUE)
  @PutMapping(value = ServicePath.PASSWORD)
  public ResponseEntity<RestResult<UserResponse>> setPassword(
      @RequestBody UserUpdatePasswordRequest request) {
    new ViolationExtractor<>(request).validate();
    request.preRequest();
    RestResult<UserResponse> result = new RestResult<>();
    result.setData(userService.setPassword(request));
    result.ok("Doi mat khau thanh cong");
    return new ResponseEntity<RestResult<UserResponse>>(result, HttpStatus.OK);
  }

//  @GetMapping(ServicePath.USER_ID + ServicePath.ACL + ServicePath.GROUP)
//  public ResponseEntity<RestResult<List<AclGroupResponse>>> getUsers(
//      @PathVariable Long userId
//  ) {
//    RestResult<List<AclGroupResponse>> result = new RestResult<>();
//    result.ok("Get all acl groups of user");
//    result.setData(userService.getAclGroups(userId));
//    return new ResponseEntity<>(result, HttpStatus.OK);
//  }
//
//  @PutMapping(ServicePath.USER_ID + ServicePath.ACL + ServicePath.GROUP + ServicePath.GROUP_ID)
//  public ResponseEntity<RestResult<Boolean>> putAclGroup(
//      @PathVariable Long userId,
//      @PathVariable Long groupId,
//      @RequestParam Boolean checked
//  ) {
//    RestResult<Boolean> result = new RestResult<>();
//    result.ok("Put user acl group success");
//    result.setData(userService.putAclGroup(userId, groupId, checked));
//    return new ResponseEntity<>(result, HttpStatus.OK);
//  }

//  @PutMapping(
//      ServicePath.USER_ID + ServicePath.ACL + ServicePath.PERMISSION + ServicePath.PERMISSION_ID)
//  public ResponseEntity<RestResult<Boolean>> putAclPermission(
//      @PathVariable Long userId,
//      @PathVariable Long permissionId,
//      @RequestParam Boolean checked
//  ) {
//    RestResult<Boolean> result = new RestResult<>();
//    result.ok("Put user acl group success");
//    result.setData(userService.putAclPermission(userId, permissionId, checked));
//    return new ResponseEntity<>(result, HttpStatus.OK);
//  }

  @GetMapping(ServicePath.ACL)
  public ResponseEntity<RestResult<Boolean>> getAclActions(
      @RequestParam String component
  ) {
    RestResult<Boolean> result = new RestResult<>();
    result.ok("Check access ui component");
    result.setData(userService.checkAccessUIComponent(component));
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

//  @GetMapping(ServicePath.ACL + ServicePath.ALL)
//  public ResponseEntity<RestResult<List<AclActionResponse>>> getAllAclActions() {
//    RestResult<List<AclActionResponse>> result = new RestResult<>();
//    result.ok("Check access ui component");
//    result.setData(userService.getAllAccessUIComponent());
//    return new ResponseEntity<>(result, HttpStatus.OK);
//  }
}
