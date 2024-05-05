package com.friendsplushies.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author vu@investidea.tech
 */
@Configuration
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .securitySchemes(Arrays.asList(tokenAccessKey()))
        .securityContexts(Collections.singletonList(securityContext()))
        .useDefaultResponseMessages(false)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.friendsplushies"))
        .build()
        .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Friends and Plushies API")
        .description("Friends and Plushies API").version("2.0")
        .contact(new Contact("Quan Vu Minh", "", "quanvm@gmail.com"))
        .build();
  }

  @Bean
  SecurityScheme tokenAccessKey() {
    return new ApiKey("Authorization", "Authorization", "header");
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/.*")).build();
  }

  private List<SecurityReference> defaultAuth() {
    final AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    final AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
    return Collections.singletonList(new SecurityReference("Authorization", authorizationScopes));
  }
}
