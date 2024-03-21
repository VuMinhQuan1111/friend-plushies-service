package com.friendsplushies.repository;

import com.friendsplushies.model.entity.User;
import com.friendsplushies.repository.custom.UserRepositoryCustom;
import com.friendsplushies.util.cruds.repository.IRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Author: chautn on 4/9/2019 2:41 PM
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, IRepository<User>, UserRepositoryCustom {

  @Query("FROM User where lower(username) = :username")
  User findByUsername(@Param("username") String username);

  @Query("FROM User where email = :email")
  User findByEmail(@Param("email") String email);

  @Query("FROM User where userId = :userId")
  User findByUserId(@Param("userId") Long userId);

  @Query("FROM User where facebookId = :facebookId")
  User findByFacebookId(@Param("facebookId") String facebookId);

  @Query("FROM User where googleId = :googleId")
  User findByGoogleId(@Param("googleId") String googleId);

//  @Query("FROM User where code = :code and isDeleted = false ")
//  User findFirstByCode(@Param("code") String code);

  @Query("FROM User WHERE name = :name")
  User findByName(@Param("name") String name);
}
