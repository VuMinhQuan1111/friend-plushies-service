package com.friendsplushies.config.oauth2;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 * Author: chautn on 6/6/2018 5:21 PM
 */
@Getter
@Setter
public class SOAuth2Request {

  private String clientId;
  private String grantType;
  private String username;
  private String email;
  private String password;
  private String userType;
  private Boolean isAdmin;
  private String token;

  public Map<String, String> toParamsMap() {
    Map<String, String> params = new HashMap<>();
    if (clientId != null) {
      params.put("client_id", clientId);
    }
    if (grantType != null) {
      params.put("grant_type", grantType);
    }
    if (username != null) {
      params.put("username", username);
    }
    if (email != null) {
      params.put("email", email);
    }
    if (password != null) {
      params.put("password", password);
    }
    if (token != null) {
      params.put("token", token);
    }
    if (isAdmin != null) {
      params.put("isAdmin", String.valueOf(isAdmin));
    }
    if (userType != null) {
      params.put("userType", userType);
    }
    return params;
  }

  public static SOAuth2Request fromParams(Map<String, String> params) {
    if (params != null && params.size() > 0) {
      SOAuth2Request request = new SOAuth2Request();
      request.setClientId(params.get("client_id"));
      request.setGrantType(params.get("grant_type"));
      request.setUsername(params.get("username"));
      request.setEmail(params.get("email"));
      request.setPassword(params.get("password"));
      request.setToken(params.get("token"));
      request.setToken(params.get("userType"));
      request.setToken(params.get("isAdmin"));
      return request;
    } else {
      return null;
    }
  }
}
