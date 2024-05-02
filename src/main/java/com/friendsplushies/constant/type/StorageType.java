package com.friendsplushies.constant.type;

public enum StorageType {
  BANNER("banner"),
  PROFILE("profile"),
  AVATAR("avatar"),
  PHOTO("photo"),
  CATEGORY("category");

  String value;

  StorageType(String value) {
    this.value = value;
  }

  public String value() {
    return value;
  }
}
