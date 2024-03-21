package com.friendsplushies.config.oauth2;

import java.io.Serializable;
import java.security.Principal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Author: chautn on 6/6/2018 3:37 PM
 */
@Getter
@Setter
@ToString
public class SUserDetails implements Principal, Serializable {

  private Long userId;
  private String username;
  private String type;
  private String name;

}
