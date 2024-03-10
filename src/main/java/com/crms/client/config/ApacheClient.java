package com.crms.client.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ApacheClient {

  private static final Logger logger = LoggerFactory.getLogger(ApacheClient.class);

  @Autowired
  private HttpClient httpClient;

  protected <T> T execute(ClassicHttpRequest request, Class<T> responseClass) throws IOException {
    final HttpClientResponseHandler<T> responseHandler = response -> {
      final int status = response.getCode();
      if (status >= HttpStatus.SC_SUCCESS && status < HttpStatus.SC_REDIRECTION) {
        final HttpEntity entity = response.getEntity();
        return new ObjectMapper().readValue(entity.getContent(), responseClass);
      } else {
        throw new ClientProtocolException("Unexpected response status: " + status);
      }
    };

    return httpClient.execute(request, responseHandler);
  }
}
