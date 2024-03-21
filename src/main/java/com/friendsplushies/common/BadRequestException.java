package com.friendsplushies.common;

import lombok.Getter;

/**
 * Author: chautn on 6/12/2018 6:12 PM
 */
public class BadRequestException extends RuntimeException {

  @Getter
  private String code;
  @Getter
  private String[] messages;

  public BadRequestException() {
    super("Invalid request");
  }

  public BadRequestException(String message) {
    super(message);
  }

  public BadRequestException(String[] messages) {
    this.messages = messages;
  }

  public BadRequestException(String message, String code) {
    super(message);
    this.code = code;
  }

  public BadRequestException(String[] messages, String code) {
    this(messages);
    this.code = code;
  }
}
