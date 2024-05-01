package com.friendsplushies.repository;

import com.friendsplushies.model.entity.UserTypePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: chautn on 4/14/2019 10:04 PM
 */
@Repository
public interface UserTypePermissionRepository extends JpaRepository<UserTypePermission, Long> {

  List<UserTypePermission> findByUserType(String userType);

  UserTypePermission findFirstByUserType(String userType);
}
