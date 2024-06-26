package com.friendsplushies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author vu@investidea.tech
 */
@SpringBootApplication
@EnableSwagger2
@EnableAsync
public class ApplicationServer {

  public static void main(String[] args) {
    SpringApplication.run(ApplicationServer.class, args);
  }
}
