package com.crms.repository;

import java.util.List;

/**
 * @author chautn on 9/22/2017
 */
public interface AccessTokenRepository {

  void removeAccessToken(List<String> username);
}
