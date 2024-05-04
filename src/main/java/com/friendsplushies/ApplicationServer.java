package com.friendsplushies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableAsync;

import com.friendsplushies.connector.StorageConnector;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author vu@investidea.tech
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableSwagger2
@EnableAsync
@ComponentScan(basePackages = "com.friendsplushies.connector", includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = StorageConnector.class))
public class ApplicationServer {

  public static void main(String[] args) {
    SpringApplication.run(ApplicationServer.class, args);
  }
}
