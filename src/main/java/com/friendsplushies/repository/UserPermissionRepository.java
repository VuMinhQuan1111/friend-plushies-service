package com.friendsplushies.repository;

import com.friendsplushies.model.entity.UserPermission;
import com.friendsplushies.repository.custom.UserPermissionRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: chautn on 4/14/2019 10:03 PM
 */
@Repository
public interface UserPermissionRepository extends JpaRepository<UserPermission, Long>, UserPermissionRepositoryCustom {

  List<UserPermission> findByUserId(Long userId);
  UserPermission findFirstByUserIdAndPermissionName(Long userId, String permissionName);
}
