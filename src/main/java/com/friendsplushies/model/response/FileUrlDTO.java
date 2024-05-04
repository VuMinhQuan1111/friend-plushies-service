package com.friendsplushies.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileUrlDTO {
    private String fileName;
  private String url;

  public FileUrlDTO(String fileName, String url) {
    this.fileName = fileName;
    this.url = url;
  }

  public FileUrlDTO() {
  }
}
