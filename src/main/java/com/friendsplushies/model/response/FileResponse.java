package com.friendsplushies.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileResponse {

  private String name;
  private String url;
  private Boolean isPublic;
  private Timestamp createdDate;
  private String createdBy;
}
