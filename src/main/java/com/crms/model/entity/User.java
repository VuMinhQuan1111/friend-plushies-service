package com.crms.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.crms.annotation.IgnorePreparingRequest;
import com.crms.model.entity.listener.FEntity;
import com.crms.model.entity.listener.FEntityListener;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import static javax.persistence.FetchType.LAZY;

/**
 * Author: chautn on 4/9/2019 1:54 PM
 */
@Getter
@Setter
@Entity
@Table(name = "user")
//@SQLDelete(sql = "UPDATE \"user\" SET is_deleted = true WHERE user_id = ?", check = ResultCheckStyle.COUNT)
@EntityListeners(FEntityListener.class)
public class User implements Serializable, FEntity {

  @Id
  @SequenceGenerator(name = "userGenerator", sequenceName = "user_user_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "userGenerator")
  @Column(name = "user_id", unique = true, nullable = false)
  private Long userId;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "temp_password")
  private String tempPassword;

  @Column(name = "type")
  private String type;

  @Column(name = "name")
  @IgnorePreparingRequest
  private String name;

  @Column(name = "phone")
  private String phone;

  @Column(name = "email")
  @IgnorePreparingRequest
  private String email;

  @Column(name = "address", columnDefinition = "TEXT")
  private String address;

  @Column(name = "title")
  private String title;

  @Column(name = "facebook_id")
  private String facebookId;

  @Column(name = "google_id")
  private String googleId;

  @Column(name = "login_count")
  private Integer loginCount;

  @Column(name = "gender")
  private String gender;

//  @Column(name = "office_id")
//  private Long officeId;

//  @ManyToMany(fetch = FetchType.LAZY)
//  @JoinTable(name = "user_permission",
//      joinColumns = @JoinColumn(name = "user_id", nullable = false, updatable = false),
//      inverseJoinColumns = @JoinColumn(name = "permission_name", referencedColumnName = "permission_name"
//          , nullable = false, updatable = false, insertable = false)
//  )
//  private Set<Permission> permissions;
//
//  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//  private Set<UserPermission> userPermissions;

  @Column(name = "status")
  @IgnorePreparingRequest
  private String status;

  @Column(name = "created_by")
  private String createdBy;

  @Column(name = "created_date")
  private Timestamp createdDate;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

//  @Column(name = "is_deleted")
//  private Boolean isDeleted = false;
//
//  @Column(name = "is_visible")
//  private Boolean isVisible = true;

//  private String linkedin;
//
//  private String facebook;
//
//  private String twitter;
//
//  private String youtube;
//
//  private String whatsapp;
//
  private String mobile;

  private String position;

//  @OneToMany(fetch = FetchType.LAZY)
//  @JoinTable(
//      name = "acl_user_group",
//      joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
//      inverseJoinColumns = {@JoinColumn(name = "acl_group_id", referencedColumnName = "acl_group_id", unique = true)}
//  )
//  private List<AclGroup> aclGroups;


//  @OneToMany(fetch = FetchType.LAZY)
//  @JoinTable(
//      name = "acl_user_permission",
//      joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
//      inverseJoinColumns = {@JoinColumn(name = "acl_permission_id", referencedColumnName = "acl_permission_id", unique = true)}
//  )
//  private List<AclPermission> aclPermissions;

//  @Column(name = "daily_goal")
//  private Long dailyGoal;
}
