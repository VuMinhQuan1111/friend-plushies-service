package com.friendsplushies.repository.custom.impl;

import com.friendsplushies.model.entity.User;
import com.friendsplushies.repository.custom.UserRepositoryCustom;
import com.friendsplushies.util.cruds.repository.impl.AbstractRepositoryImpl;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.scheduling.annotation.Async;

/**
 * @author vuld
 * @date 19/08/2020
 */
public class UserRepositoryImpl extends AbstractRepositoryImpl<User> implements UserRepositoryCustom {

  public UserRepositoryImpl() {
    super(User.class);
  }

  @Override
  @Transactional
  @Async
  public void addAclGroup(Long userId, Long aclGroupId) {
    String sql = "insert into acl_user_group(user_id, acl_group_id) values (:userId, :aclGroupId)";
    Query query = entityManager.createNativeQuery(sql);
    query.setParameter("userId", userId);
    query.setParameter("aclGroupId", aclGroupId);
    query.executeUpdate();
  }

  @Override
  @Transactional
  @Async
  public void removeAclGroup(Long userId, Long aclGroupId) {
    String sql = "delete from acl_user_group where user_id = :userId " +
        (aclGroupId != null ? "and acl_group_id = :aclGroupId" : "");
    Query query = entityManager.createNativeQuery(sql);
    query.setParameter("userId", userId);
    if (aclGroupId != null) {
      query.setParameter("aclGroupId", aclGroupId);
    }
    query.executeUpdate();
  }

  @Override
  @Transactional
  @Async
  public void addAclPermission(Long userId, Long aclPermissionId) {
    String sql = "insert into acl_user_permission(user_id, acl_permission_id) values (:userId, :aclPermissionId)";
    Query query = entityManager.createNativeQuery(sql);
    query.setParameter("userId", userId);
    query.setParameter("aclPermissionId", aclPermissionId);
    query.executeUpdate();
  }

  @Override
  @Transactional
  @Async
  public void removeAclPermission(Long userId, Long aclPermissionId) {
    String sql = "delete from acl_user_permission where user_id = :userId " +
        (aclPermissionId != null ? "and acl_permission_id = :aclPermissionId" : "");
    Query query = entityManager.createNativeQuery(sql);
    query.setParameter("userId", userId);
    if (aclPermissionId != null) {
      query.setParameter("aclPermissionId", aclPermissionId);
    }
    query.executeUpdate();
  }

}
