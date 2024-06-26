package com.friendsplushies.config;

import com.friendsplushies.connector.StorageConnector;
import com.friendsplushies.connector.impl.LocalStorageConnector;
import com.friendsplushies.util.ResourceBundle;
import com.sendgrid.SendGrid;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;

/**
 * @author vuld
 * @date 19/08/2020
 */
@Configuration
public class WebConfig {
  @Bean
  public StorageConnector storageConnector() {
    return new LocalStorageConnector(); // Assuming StorageConnector has a default constructor
  }

  @Bean
  ResourceBundle resourceBundle() {
    return new ResourceBundle(messageSource());
  }

  @Bean
  ResourceBundleMessageSource messageSource() {
    ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
    resourceBundleMessageSource.setBasename("messages.messages");
    resourceBundleMessageSource.setDefaultEncoding("UTF-8");
    return resourceBundleMessageSource;
  }
  

  @Autowired
  private Environment env;

  @Bean
  public Validator validator() {
    return Validation.buildDefaultValidatorFactory().getValidator();
  }

  @Bean
  public SendGrid sendGrid() {
    return new SendGrid(env.getProperty("sendgrid.api_key", "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"));
  }
}
