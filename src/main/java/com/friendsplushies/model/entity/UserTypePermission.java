package com.friendsplushies.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Author: chautn on 4/14/2019 2:51 PM
 */
@Getter
@Setter
@Entity
@Table(name = "user_type_permission")
public class UserTypePermission {

  @Id
  @SequenceGenerator(name = "userTypePermissionGenerator", sequenceName = "user_type_permission_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "userTypePermissionGenerator")
  @Column(name = "user_type_permission_id", unique = true, nullable = false)
  private Long userTypePermissionId;

  @Column(name = "user_type")
  private String userType;

//  @Column(name = "permission_name")
//  private String permissionName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "permission_name", referencedColumnName = "permission_name")
  private Permission permission;
}
