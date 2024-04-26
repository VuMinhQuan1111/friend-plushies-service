package com.friendsplushies.service.impl;

import com.friendsplushies.common.BadRequestException;
import com.friendsplushies.config.oauth2.SAuthentication;
import com.friendsplushies.config.oauth2.SOAuth2Request;
import com.friendsplushies.config.oauth2.SUserDetails;
import com.friendsplushies.constant.type.UserType;
import com.friendsplushies.model.entity.User;
import com.friendsplushies.model.mapper.UserMapper;
import com.friendsplushies.model.request.user.UserActivateRequest;
import com.friendsplushies.model.request.user.UserCreateRequest;
import com.friendsplushies.model.request.user.UserRequest;
import com.friendsplushies.model.request.user.UserResetPasswordRequest;
import com.friendsplushies.model.request.user.UserUpdatePasswordRequest;
import com.friendsplushies.model.request.user.UserUpdateRequest;
import com.friendsplushies.model.response.UserResponse;
//import com.crms.model.response.acl.AclActionResponse;
//import com.crms.model.response.acl.AclGroupResponse;
import com.friendsplushies.repository.AccessTokenRepository;
import com.friendsplushies.repository.UserRepository;
import com.friendsplushies.service.UserService;
import com.friendsplushies.util.MappingUtil;
import com.friendsplushies.util.cruds.service.impl.AbstractServiceImpl;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import javax.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;

/**
 * Author: chautn on 4/9/2019 3:20 PM
 */
@Service
public class UserServiceImpl extends AbstractServiceImpl<UserRequest, UserResponse, User> implements
    UserService {

  TokenStore tokenStore;

  private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

  public UserServiceImpl() {
  }

  @Autowired
  public UserServiceImpl(UserRepository userRepository, TokenStore tokenStore) {
    super(userRepository, UserMapper.INSTANCE, User.class);
    this.tokenStore = tokenStore;
  }

  @Autowired
  private TokenEndpoint tokenEndpoint;

  @Autowired
  private ConsumerTokenServices tokenServices;

  @Autowired
  private UserRepository userRepository;

//  @Autowired
//  private UserPermissionRepository userPermissionRepository;
//
//  @Autowired
//  private UserTypePermissionRepository userTypePermissionRepository;

  @Autowired
  private AccessTokenRepository accessTokenRepository;

//  @Autowired
//  private AclGroupService aclGroupService;
//
//  @Autowired
//  private AclPermissionService aclPermissionService;
//
//  @Autowired
//  private AclActionService aclActionService;
//
//  @Autowired
//  private AclPermissionRepository aclPermissionRepository;
//
//  @Autowired
//  private AclGroupRepository aclGroupRepository;

  @Override
  public UserResponse register(UserCreateRequest userCreateRequest) throws IllegalAccessException {
    User existEmail = userRepository.findByEmail(userCreateRequest.getEmail());
    if (existEmail != null) {
      if (StringUtils.isNotEmpty(userCreateRequest.getEmail()) && userCreateRequest.getEmail()
          .equals(existEmail.getEmail())) {
        throw new BadRequestException("Email already existed");
      }
    }
    if (StringUtils.isNotEmpty(userCreateRequest.getUsername())) {
      User existUsername = userRepository.findByUsername(userCreateRequest.getUsername());
      String username = userCreateRequest.getUsername();
      while (existUsername != null && username
          .equals(existUsername.getUsername())) {
        char last = username.charAt(username.length() - 1);
        if (NumberUtils.isNumber(String.valueOf(last))) {
          username = username.substring(0, username.length() - 1);
          username = username + (Integer.valueOf(String.valueOf(last)) + 1);
        } else {
          username = username + 1;
        }

        existUsername = userRepository.findByUsername(username);
      }
      userCreateRequest.setUsername(username);
    }
    User user = UserMapper.INSTANCE.toEntity(userCreateRequest);
//    user.setStatus(Status.ACTIVE.name());
//    UserType userType = UserType.userType(userCreateRequest.getType());
//    if (userType == UserType.ANONYMOUS || userType == null) {
//      userType = UserType.USER;
//    }
//    user.setType(userType.name());
//    if (StringUtils.isEmpty(user.getName())) {
//      user.setName(user.getFirstName() + " " + user.getLastName());
//    }
//    if (CollectionUtils.isNotEmpty(userCreateRequest.getAclPermissionIds())) {
//      SearchRequest searchPermissions = new SearchRequest();
//      searchPermissions.setConditionType(ConditionType.AND);
//      searchPermissions.setConditions(Collections.singletonList(
//          CustomTriple.of("aclPermissionId", "IN", userCreateRequest.getAclPermissionIds())
//      ));
////      List<AclPermission> permissions = aclPermissionRepository.searchRequest(searchPermissions);
////      if (CollectionUtils.isNotEmpty(permissions)) {
////        user.setAclPermissions(permissions);
////      }
//    }
//    if (CollectionUtils.isNotEmpty(userCreateRequest.getAclGroupIds())) {
//      SearchRequest searchGroups = new SearchRequest();
//      searchGroups.setConditionType(ConditionType.AND);
//      searchGroups.setConditions(Collections.singletonList(
//          CustomTriple.of("aclGroupId", "IN", userCreateRequest.getAclGroupIds())
//      ));
//      List<AclGroup> groups = aclGroupRepository.searchRequest(searchGroups);
//      if (CollectionUtils.isNotEmpty(groups)) {
//        user.setAclGroups(groups);
//      }
//    }
    user = userRepository.save(user);
    if (user == null) {
      throw new BadRequestException("Cannot registered");
    }

//    //create permission
//    UserTypePermission userTypePermission = userTypePermissionRepository
//        .findFirstByUserType(userType.name());
//    if (userTypePermission == null) {
//      throw new BadRequestException("Cannot create permission");
//    }
//    UserPermission userPermission = new UserPermission();
//    userPermission.setUserId(user.getUserId());
//    userPermission.setPermissionName(userTypePermission.getPermission().getPermissionName());
//    userPermissionRepository.save(userPermission);
    return UserMapper.INSTANCE.toResponse(user);
  }

  @Override
  public OAuth2AccessToken login(SOAuth2Request request)
      throws HttpRequestMethodNotSupportedException {
    return tokenEndpoint.postAccessToken(new SAuthentication(request, null), request.toParamsMap())
        .getBody();
  }

  @Override
  public void logout() {
    OAuth2AuthenticationDetails authDetails = (OAuth2AuthenticationDetails) SecurityContextHolder
        .getContext().getAuthentication().getDetails();
    tokenServices.revokeToken(authDetails.getTokenValue());
  }

  @Override
  public SUserDetails details() {
    SUserDetails details = null;
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication instanceof OAuth2Authentication) {
      details = (SUserDetails) authentication.getPrincipal();
    }
    if (details == null) {
      details = new SUserDetails();
      details.setType(UserType.ANONYMOUS.name());
    }
    return details;
  }

  @Override
  public UserResponse me() {
    UserResponse response = null;
    SUserDetails details = this.details();
    if (!UserType.ANONYMOUS.name().equals(details.getType())) {
      User user = userRepository.getOne(details.getUserId());
//      if (user.getUserId() != null) {
//        List<AclAction> aclAction = aclActionService
//            .getAllAccessUIComponent(user.getUserId());
        response = UserMapper.INSTANCE.toResponse(user);
//        response.setAclActions(AclActionMapper.INSTANCE.toResponses(aclAction));
//      }
    }
    if (response == null) {
      response = new UserResponse();
      response.setType(UserType.ANONYMOUS.name());
//      response.setTitle(UserType.ANONYMOUS.getTitle());
    }
    return response;
  }

  @Transactional
  public UserResponse updateUser(UserUpdateRequest userUpdateRequest) throws IllegalAccessException {
    User user = this.getUser(userUpdateRequest.getUserId());
    User request = UserMapper.INSTANCE.toEntity(userUpdateRequest);
    if (StringUtils.isNotBlank(userUpdateRequest.getEmail())
        && !userUpdateRequest.getEmail().equals(user.getEmail())) {
      User userByEmail = userRepository.findByEmail(userUpdateRequest.getEmail());
      if (userByEmail != null) {
        throw new IllegalArgumentException("Email already existed");
      }
    }

    if (StringUtils.isNotBlank(userUpdateRequest.getUsername())
        && !userUpdateRequest.getUsername().equals(user.getUsername())) {
      User userByUsername = userRepository.findByUsername(userUpdateRequest.getUsername());
      if (userByUsername != null) {
        throw new IllegalArgumentException("Username already existed");
      }
    }

    MappingUtil.setParameter(User.class, request, user);

    userRepository.save(user);
    UserResponse userResponse = UserMapper.INSTANCE.toResponse(user);
    return userResponse;
  }

  @Override
  public UserResponse setPassword(UserUpdatePasswordRequest request) {
    User user = userRepository.findByEmail(request.getEmail());
    if (user == null || user.getUserId() == null) {
      throw new BadRequestException("Account not existed");
    }
//    } else if (!user.getStatus().equalsIgnoreCase("ACTIVE")) {
//      throw new BadRequestException("Account have been deleted");
//    }
    if (StringUtils.isNotBlank(user.getPassword())) {
      if (StringUtils.isNotBlank(request.getOldPassword())) {
        validateByOldPassword(user, request);
      } else if (StringUtils.isNotBlank(request.getOtp()) && StringUtils
          .isNotBlank(request.getToken())) {
        validateByOtp(user, request);
      } else {
        throw new BadRequestException("Not supported yet");
      }
    }
    user.setPassword(request.getPassword());
    UserResponse userResponse = UserMapper.INSTANCE.toResponse(userRepository.save(user));
    return userResponse;
  }

  @Override
  public void resetPassword(UserResetPasswordRequest request) {
//    ResetPasswordMethod resetMethod = ResetPasswordMethod.method(request.getMethod());
//    if (resetMethod == null) {
//      throw new BadRequestException("Not supported yet");
//    }
//    switch (resetMethod) {
//      case EMAIL:
//        if (StringUtils.isNotBlank(request.getEmail())) {
//          User user = userRepository.findByEmail(request.getEmail());
//          if (user == null) {
//            throw new BadRequestException("Email is not existed");
//          }
//          if (StringUtils.isBlank(user.getTempPassword())) {
//            user.setTempPassword(RandomStringUtils.random(8, true, true).toUpperCase());
//          }
//          userRepository.save(user);
//
//          Map<String, String> params = new HashMap<>();
//          params.put("NEW_PASSWORD", user.getTempPassword());
//          emailService.sendEmail(request.getEmail(), EmailTemplateCode.FORGOT_PASSWORD, params);
//        } else {
//          throw new BadRequestException("Email is required");
//        }
//        return;
//      default:
//        throw new BadRequestException("Not supported yet");
//    }
  }

  @Override
  public UserResponse activate(UserActivateRequest request)
      throws IllegalAccessException, InvocationTargetException {
    UserResponse user = super.update(request);
    if (request.getActive() == null || !request.getActive()) {
      accessTokenRepository
          .removeAccessToken(Collections.singletonList(user.getType() + "_" + user.getUserId()));
    }
    return user;
  }

//  @Override
//  public Boolean putAclGroup(Long userId, Long groupId, Boolean checked) {
//    User user = getUser(userId);
//    AclGroup group = aclGroupService.getAndValidateEntity(groupId);
//    if (checked != null) {
//      if (checked) {
//        userRepository.addAclGroup(userId, groupId);
//      } else {
//        userRepository.removeAclGroup(userId, groupId);
//      }
//    }
//    return checked;
//  }

//  @Override
//  public Boolean putAclPermission(Long userId, Long permissionId, Boolean checked) {
//    User user = getUser(userId);
//    AclPermission permission = aclPermissionService.getAndValidateEntity(permissionId);
//    if (checked != null) {
//      if (checked) {
//        userRepository.addAclPermission(userId, permissionId);
//      } else {
//        userRepository.removeAclPermission(userId, permissionId);
//      }
//    }
//    return checked;
//  }

//  @Override
//  public List<AclGroupResponse> getAclGroups(Long userId) {
//    getUser(userId);
//    return aclGroupService.getAclGroupsByUserId(userId);
//  }

  @Override
  public Boolean checkAccessUIComponent(String component) {
//    return aclActionService.checkAccessUIComponent(details().getUserId(), component);
    return true;
  }

//  @Override
//  public List<AclActionResponse> getAllAccessUIComponent() {
//    List<AclAction> aclActions = aclActionService.getAllAccessUIComponent(details().getUserId());
//    return AclActionMapper.INSTANCE.toResponses(aclActions);
//  }

  private void validateByOtp(User user, UserUpdatePasswordRequest request) {
    if (!request.getOtp().equals("222222") || !request.getToken().equals("999999")) {
      throw new BadRequestException("Wrong Token");
    }
  }

  private void validateByOldPassword(User user, UserUpdatePasswordRequest request) {
    if (user.getUserId().equals(details().getUserId())) {
      if (!user.getPassword().equals(request.getOldPassword())) {
        throw new BadRequestException("Incorrect old password");
      }
    } else {
      throw new OAuth2Exception("Cannot change password when not logged in");
    }
  }

  private User getUser(Long userId) {
    return userRepository.findById(userId).orElseThrow(
        () -> new IllegalArgumentException("User does not exist: " + userId)
    );
  }

  @Override
  @Transactional
  public UserResponse update(UserRequest userRequest)
      throws IllegalAccessException, InvocationTargetException {
    if (StringUtils.isNotEmpty(userRequest.getPassword())) {
      userRequest.setPassword(DigestUtils.md5DigestAsHex(userRequest.getPassword().getBytes()));
    }

    userRequest.preRequest();

    User user = this.getUser(userRequest.getUserId());
    User request = UserMapper.INSTANCE.toEntity(userRequest);
    if (StringUtils.isNotBlank(userRequest.getEmail())
        && !userRequest.getEmail().equals(user.getEmail())) {
      User userByEmail = userRepository.findByEmail(userRequest.getEmail());
      if (userByEmail != null) {
        throw new IllegalArgumentException("Email already existed");
      }
    }

    if (StringUtils.isNotBlank(userRequest.getUsername())
        && !userRequest.getUsername().equals(user.getUsername())) {
      User userByUsername = userRepository.findByUsername(userRequest.getUsername());
      if (userByUsername != null) {
        throw new IllegalArgumentException("Username already existed");
      }
    }

    MappingUtil.setParameter(User.class, request, user);
    userRepository.save(user);
    UserResponse userResponse = UserMapper.INSTANCE.toResponse(user);
    return userResponse;
  }
}
