package com.friendsplushies.config.oauth2;

import com.friendsplushies.model.entity.User;
import com.friendsplushies.repository.UserRepository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.util.DigestUtils;

/**
 * Author: chautn on 6/6/2018 4:57 PM
 */
public class SAuthenticationManager implements AuthenticationManager {

  @Autowired
  private Environment env;

  @Autowired
  private UserRepository userRepository;

  @Override
  public Authentication authenticate(Authentication authentication) {
    SAuthentication sAuth = (SAuthentication) authentication;

    User user = loadUserProfile(sAuth.getCredentials());
    if (user == null || user.getUserId() == null) {
      throw new OAuth2Exception("Username not existed");
    }

    // return authentication
    SUserDetails userDetails = new SUserDetails();
    userDetails.setUserId(user.getUserId());
//    userDetails.setType(user.getType());
    userDetails.setUsername(user.getUsername());
    userDetails.setName(user.getName());

    sAuth.setPrincipal(userDetails);
//    sAuth.getAuthorities().addAll(AuthorityUtils.createAuthorityList(fetchPermissions(user)));
    sAuth.setAuthenticated(true);
    sAuth.eraseCredentials();
    return authentication;
  }

//  private String[] fetchPermissions(User user) {
//    Set<Permission> permissions = new HashSet<>();
//    permissions.addAll(getPermissionsByUserType(user.getType()));
//    permissions.addAll(getPermissionsOfUser(user.getUserId()));
//    return permissions.stream().map(Permission::getPermissionName).distinct().toArray(String[]::new);
//  }

  private void verifyPassword(SOAuth2Request request, User user) {
    if (StringUtils.isBlank(request.getPassword())) {
      throw new OAuth2Exception("Password is required");
    }
    String md5InputPassword = DigestUtils.md5DigestAsHex(request.getPassword().getBytes());
    if (!md5InputPassword.equals(user.getPassword()) && !request.getPassword().equals(user.getTempPassword())) {
      throw new OAuth2Exception("Password is incorrect");
    }
  }

  private User loadUserProfile(SOAuth2Request request) {
    User user;
    switch (request.getGrantType()) {
      case "password":
        user = userRepository.findByUsername(request.getUsername().trim().toLowerCase());
        if (user == null) {
          user = userRepository.findByEmail(request.getUsername().trim().toLowerCase());
        }
        if (user == null) {
          return null;
        }
//        if(user.getStatus().equalsIgnoreCase("INACTIVE")){
//          throw new OAuth2Exception("User have been locked");
//        }
        verifyPassword(request, user);
        return user;
      case "email":
        user = userRepository.findByEmail(request.getEmail().trim().toLowerCase());
        verifyPassword(request, user);
        return user;
      default:
        return null;
    }
  }

//  private Set<Permission> getPermissionsByUserType(String userType) {
//    List<UserTypePermission> userTypePermissions = userTypePermissionRepository.findByUserType(userType);
//    if (CollectionUtils.isNotEmpty(userTypePermissions)) {
//      return userTypePermissions.stream().map(UserTypePermission::getPermission).collect(Collectors.toSet());
//    } else {
//      return Collections.emptySet();
//    }
//  }
//
//  private Set<Permission> getPermissionsOfUser(Long userId) {
//    List<UserPermission> userPermissions = userPermissionRepository.findByUserId(userId);
//    if (CollectionUtils.isNotEmpty(userPermissions)) {
//      return userPermissions.stream().map(UserPermission::getPermission).collect(Collectors.toSet());
//    } else {
//      return Collections.emptySet();
//    }
//  }
}
