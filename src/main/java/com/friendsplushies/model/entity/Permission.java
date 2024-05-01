package com.friendsplushies.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Author: chautn on 4/14/2019 2:42 PM
 */
@Getter
@Setter
@Entity
@Table(name = "permission")
public class Permission implements Serializable {

  @Id
  @SequenceGenerator(name = "permissionGenerator", sequenceName = "permission_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "permissionGenerator")
  @Column(name = "permission_id", unique = true, nullable = false)
  private Long permissionId;

  @Column(name = "permission_name")
  private String permissionName;

  @Column(name = "description")
  private String description;

  @Column(name = "access_api")
  private String accessApi;
}
