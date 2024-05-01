package com.friendsplushies.config.oauth2;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

/**
 * Author: chautn on 6/7/2018 11:36 AM
 */
public class STokenGranter implements TokenGranter {

  private final AuthenticationManager authenticationManager;
  private final ClientDetailsService clientDetailsService;
  private final AuthorizationServerTokenServices tokenServices;
  private final OAuth2RequestFactory requestFactory;

  public STokenGranter(
          AuthenticationManager authenticationManager,
          ClientDetailsService clientDetailsService,
          AuthorizationServerTokenServices tokenServices,
          OAuth2RequestFactory requestFactory
  ) {
    this.authenticationManager = authenticationManager;
    this.clientDetailsService = clientDetailsService;
    this.tokenServices = tokenServices;
    this.requestFactory = requestFactory;
  }

  @Override
  public OAuth2AccessToken grant(String grantType, TokenRequest tokenRequest) {
    ClientDetails client = clientDetailsService.loadClientByClientId(tokenRequest.getClientId());
    validateTokenRequest(grantType, client);
    SOAuth2Request authRequest = SOAuth2Request.fromParams(tokenRequest.getRequestParameters());
    SAuthentication sAuth = new SAuthentication(authRequest, getClientAuthorities(client));
    OAuth2Request storedOAuth2Request = requestFactory.createOAuth2Request(client, tokenRequest);
    OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(storedOAuth2Request, authenticationManager.authenticate(sAuth));
    return tokenServices.createAccessToken(oAuth2Authentication);
  }

  private Set<GrantedAuthority> getClientAuthorities(ClientDetails client) {
    Set<GrantedAuthority> authorities = new HashSet<>(client.getAuthorities());
    if (CollectionUtils.isNotEmpty(client.getScope())) {
      for (String scope : client.getScope()) {
        authorities.add(new SimpleGrantedAuthority(scope));
      }
    }
    return authorities;
  }

  private void validateTokenRequest(String grantType, ClientDetails clientDetails) {
    Collection<String> authorizedGrantTypes = clientDetails.getAuthorizedGrantTypes();
    if (authorizedGrantTypes != null && !authorizedGrantTypes.isEmpty() && !authorizedGrantTypes.contains(grantType)) {
      throw new InvalidClientException("Invalid grantType");
    }
  }

}