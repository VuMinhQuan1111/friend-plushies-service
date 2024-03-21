package com.friendsplushies.model.exception;

public class FileException extends Exception {

  public FileException(String message) {
    super(message);
  }

  public FileException(String message, Throwable e) {
    super(message, e);
  }
}