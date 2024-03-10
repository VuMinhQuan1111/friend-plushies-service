package com.crms.config.oauth2;

import java.util.Set;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;

/**
 * Author: chautn on 6/6/2018 5:28 PM
 */
public class SAuthentication implements Authentication, CredentialsContainer {

  private Set<GrantedAuthority> authorities;
  private SUserDetails userDetails;
  private SOAuth2Request oauth2Request;
  private boolean authenticated;

  public SAuthentication(SOAuth2Request oauth2Request, Set<GrantedAuthority> authorities) {
    this.oauth2Request = oauth2Request;
    this.authorities = authorities;
    this.authenticated = false;
  }

  @Override
  public Set<GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @Override
  public SOAuth2Request getCredentials() {
    return this.oauth2Request;
  }

  @Override
  public Object getDetails() {
    // not use this field
    return null;
  }

  @Override
  public SUserDetails getPrincipal() {
    return this.userDetails;
  }

  public void setPrincipal(SUserDetails userDetails) {
    this.userDetails = userDetails;
  }

  @Override
  public boolean isAuthenticated() {
    return this.authenticated || this.getPrincipal() == null;
  }

  @Override
  public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
    this.authenticated = authenticated;
  }

  @Override
  public String getName() {
    return this.getPrincipal() != null ?
        this.getPrincipal().getName() :
        this.getCredentials().getClientId();
  }

  @Override
  public void eraseCredentials() {
    this.oauth2Request = null;
  }
}
