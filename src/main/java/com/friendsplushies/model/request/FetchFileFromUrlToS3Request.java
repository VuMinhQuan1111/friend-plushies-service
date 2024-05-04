package com.friendsplushies.model.request;

import lombok.Data;


@Data
public class FetchFileFromUrlToS3Request {
  private String fetchedUrl;
  private String filePath;
}