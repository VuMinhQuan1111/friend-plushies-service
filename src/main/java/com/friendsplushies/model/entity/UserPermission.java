package com.friendsplushies.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Author: chautn on 4/14/2019 2:51 PM
 */
@Getter
@Setter
@Entity
@Table(name = "user_permission")
public class UserPermission implements Serializable {

  @Id
  @SequenceGenerator(name = "userPermissionGenerator", sequenceName = "user_permission_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "userPermissionGenerator")
  @Column(name = "user_permission_id", unique = true, nullable = false)
  private Long userPermissionId;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "permission_name")
  private String permissionName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "permission_name", referencedColumnName = "permission_name", updatable = false, insertable = false)
  private Permission permission;

  @Column(name = "created_date")
  private Timestamp createdDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "user_id", updatable = false, insertable = false)
  @JsonBackReference
  private User user;
}
