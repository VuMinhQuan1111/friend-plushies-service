package com.friendsplushies.constant.type;

/**
 * @author vuld
 */
public enum UserType {
  ANONYMOUS("Ẩn danh"),
  USER("Người dùng"),
  STARTER("Người dùng đang đăng ký"),
  PARTNER("Đối tác"),
  INTERMEDIARY("Trung gian bán hàng");

  private String title;

  UserType(String title) {
    this.title = title;
  }

  public static UserType userType(String type) {
    for (UserType userType : values()) {
      if (userType.name().equals(type)) {
        return userType;
      }
    }
    return ANONYMOUS;
  }

  public String getTitle() {
    return title;
  }
}
