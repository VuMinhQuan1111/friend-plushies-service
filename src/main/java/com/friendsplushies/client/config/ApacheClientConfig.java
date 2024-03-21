package com.friendsplushies.client.config;

import java.util.concurrent.TimeUnit;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: chautn on 12/3/2018 10:01 AM
 */
@Configuration
public class ApacheClientConfig {

  @Bean
  public HttpClient httpClient() {
    return HttpClientBuilder
        .create()
        .setConnectionManager(
            PoolingHttpClientConnectionManagerBuilder.create()
                .setMaxConnPerRoute(2)
                .setMaxConnTotal(20)
                .build()
        )
        .setDefaultRequestConfig(
            RequestConfig.custom()
                .setConnectTimeout(2, TimeUnit.SECONDS)
                .setConnectionRequestTimeout(5, TimeUnit.SECONDS)
                .setResponseTimeout(5, TimeUnit.SECONDS)
                .build()
        )
        .build();
  }

}
