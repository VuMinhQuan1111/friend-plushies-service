package com.friendsplushies.config;

import com.friendsplushies.config.oauth2.STokenGranter;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configuration.ClientDetailsServiceConfiguration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

/**
 * Author: chautn on 6/6/2018 6:23 PM
 */
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

  @Autowired
  private DataSource dataSource;

  @Qualifier("authenticationManagerBean")
  @Autowired
  private AuthenticationManager authenticationManager;

  @Override
  public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
    clients.withClientDetails(customClientDetailsService());
  }

  @Override
  public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.setClientDetailsService(customClientDetailsService());

    endpoints
        .tokenServices(tokenServices())
        .tokenStore(new JdbcTokenStore(dataSource))
        .authenticationManager(authenticationManager)
        .tokenGranter(
            new STokenGranter(
                authenticationManager,
                endpoints.getClientDetailsService(),
                endpoints.getTokenServices(),
                endpoints.getOAuth2RequestFactory()
            )
        );
  }

  @Bean
  public DefaultTokenServices tokenServices() {
    final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
    defaultTokenServices.setTokenStore(tokenStore());
    defaultTokenServices.setSupportRefreshToken(true);
    defaultTokenServices.setAccessTokenValiditySeconds(0);
    return defaultTokenServices;
  }

  @Bean
  public ClientDetailsService customClientDetailsService() throws Exception {
    ClientDetailsServiceConfiguration serviceConfig = new ClientDetailsServiceConfiguration();
    serviceConfig.clientDetailsServiceConfigurer().jdbc(dataSource);
    return serviceConfig.clientDetailsService();
  }

  @Bean
  public TokenStore tokenStore() {
    return new JdbcTokenStore(dataSource);
  }

}
