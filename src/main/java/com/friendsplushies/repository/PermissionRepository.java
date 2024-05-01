package com.friendsplushies.repository;

import com.friendsplushies.model.entity.Permission;
import com.friendsplushies.repository.custom.PermissionRepositoryCustom;
import com.friendsplushies.util.cruds.repository.IRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author: chautn on 4/14/2019 9:28 PM
 */
public interface PermissionRepository extends JpaRepository<Permission, Long>, IRepository<Permission>, PermissionRepositoryCustom {

  List<Permission> findDistinctByPermissionNameIn(List<String> permissionNames);
}
