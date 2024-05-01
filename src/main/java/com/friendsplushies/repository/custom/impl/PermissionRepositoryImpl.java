package com.friendsplushies.repository.custom.impl;

import com.friendsplushies.model.entity.Permission;
import com.friendsplushies.repository.custom.PermissionRepositoryCustom;
import com.friendsplushies.util.cruds.repository.impl.AbstractRepositoryImpl;

public class PermissionRepositoryImpl extends AbstractRepositoryImpl<Permission> implements PermissionRepositoryCustom {

  public PermissionRepositoryImpl() {
    super(Permission.class);
  }
}
