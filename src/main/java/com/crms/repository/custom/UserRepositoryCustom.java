package com.crms.repository.custom;

/**
 * Author: chautn on 4/9/2019 2:42 PM
 */
public interface UserRepositoryCustom {

  void addAclGroup(Long userId, Long aclGroupId);

  void removeAclGroup(Long userId, Long aclGroupId);

  void addAclPermission(Long userId, Long aclPermissionId);

  void removeAclPermission(Long userId, Long aclPermissionId);

}
