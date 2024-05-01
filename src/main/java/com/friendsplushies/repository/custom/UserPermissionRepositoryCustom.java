package com.friendsplushies.repository.custom;


import com.friendsplushies.model.entity.User;
import com.friendsplushies.model.request.UserPermissionSearchRequest;

import java.util.List;

/**
 * @author doductrung
 */
public interface UserPermissionRepositoryCustom {

  List<User> searchUserPermissions(UserPermissionSearchRequest request);

  Long countUserPermissions(UserPermissionSearchRequest request);
}
