package com.friendsplushies.config;

import com.friendsplushies.constant.type.PermissionName;
import com.friendsplushies.model.entity.Permission;
import com.friendsplushies.repository.PermissionRepository;
import com.friendsplushies.util.StringUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import java.util.*;

/**
 * @author vuld
 * @date 19/08/2020
 */
@EnableResourceServer
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  @Getter
  @Setter
  @EqualsAndHashCode
  @AllArgsConstructor
  class AccessApi {

    private String method;
    private String api;
  }

  private static final Logger logger = LoggerFactory.getLogger(ResourceServerConfig.class);

  @Autowired
  private Environment env;

  @Autowired
  private PermissionRepository permissionRepository;

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) {

  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorization = http.authorizeRequests();
    boolean enableSecurity = env.getProperty("api.enable_security", Boolean.class, true);
    boolean enableSwagger = env.getProperty("api.enable_swagger", Boolean.class, true);
    if (enableSwagger) {
      authorization.antMatchers("/swagger-ui.html", "/swagger-resources/**", "/v2/api-docs/**", "/webjars/**", "/swagger/docs/v1").permitAll();
    } else {
      authorization.antMatchers("/swagger-ui.html", "/swagger-resources/**", "/v2/api-docs/**", "/webjars/**").denyAll();
    }
    if (enableSecurity) {
      for (Map.Entry<AccessApi, Set<String>> accessAuthority : loadAccessAuthorities().entrySet()) {
        if (StringUtils.isNotEmpty(accessAuthority.getKey().getApi())) {
          authorization.antMatchers(
                  HttpMethod.resolve(accessAuthority.getKey().getMethod()),
                  accessAuthority.getKey().getApi()
          ).hasAnyAuthority(accessAuthority.getValue().toArray(new String[0]));
        }
      }
      authorization.antMatchers("/**").hasAnyAuthority(PermissionName.CLIENT_PERMISSION.name());
      authorization.antMatchers("/**").hasAnyAuthority(PermissionName.ADMIN_PERMISSION.name());
    } else {
      authorization.antMatchers("/**").permitAll();
//    }
    }
  }

    private Map<AccessApi, Set<String>> loadAccessAuthorities () {
      Gson gson = new Gson();
      Map<AccessApi, Set<String>> result = new HashMap<>();
      List<Permission> permissions = permissionRepository.findAll();
      for (Permission permission : permissions) {
        if (StringUtils.isNotBlank(permission.getAccessApi())) {
          try {
            List<Map<String, List<String>>> accessApiPermissions = Arrays.asList(gson.fromJson(permission.getAccessApi(), Map[].class));
            for (Map<String, List<String>> accessApiPermission : accessApiPermissions) {
              for (Map.Entry<String, List<String>> accessApiMethod : accessApiPermission.entrySet()) {
                HttpMethod httpMethod = HttpMethod.resolve(accessApiMethod.getKey());
                List<String> paths = accessApiMethod.getValue();
                if (httpMethod != null && CollectionUtils.isNotEmpty(paths)) {
                  for (String path : paths) {
                    AccessApi accessApi = new AccessApi(httpMethod.name(), path);
                    Set<String> authorities = result.computeIfAbsent(accessApi, k -> new HashSet<>());
                    authorities.add(permission.getPermissionName());
                  }
                }
              }
            }
          } catch (JsonSyntaxException e) {
            logger.error("Wrong permission format: " + permission.getAccessApi(), e);
          }
        }
      }
      return result;
    }
}
