package com.crms.model.request.user;

import com.crms.model.request.SearchRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author vuld
 */
@Getter
@Setter
public class UserSearchRequest extends SearchRequest {

  private Long userId;
  private String username;
  private String type;
  private String name;
  private String phone;
  private String email;
  private String address;
  private String title;
  private String office;
  private String facebookId;
  private String googleId;
  private String gender;
  private String status;
  private String createdBy;
  private Long createdDate;
  private String firstName;
  private String lastName;
  private String linkedin;
  private String facebook;
  private String twitter;
  private String youtube;
  private String whatsapp;
  private String mobile;
  private String position;

}
