package com.friendsplushies.model.entity;

import com.friendsplushies.annotation.IgnorePreparingRequest;
import com.friendsplushies.model.entity.listener.FEntity;
import com.friendsplushies.model.entity.listener.FEntityListener;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

/**
 * Author: chautn on 4/9/2019 1:54 PM
 */
@Getter
@Setter
@Entity
@Table(name = "user")
@EntityListeners(FEntityListener.class)
public class User implements Serializable, FEntity {

  @Id
  @SequenceGenerator(name = "userGenerator", sequenceName = "user_user_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "userGenerator")
  @Column(name = "user_id", unique = true, nullable = false)
  private Long userId;

  @Column(name = "user_account_name")
  private String username;

  @Column(name = "user_password")
  private String password;

  @Column(name = "temp_password")
  private String tempPassword;

  @Column(name = "dob")
  private Date dob;

  @Column(name = "user_full_name")
  @IgnorePreparingRequest
  private String name;

  @Column(name = "user_phone")
  private String phone;

  @Column(name = "email")
  @IgnorePreparingRequest
  private String email;

  @Column(name = "user_address", columnDefinition = "TEXT")
  private String address;

  @Column(name = "created_by")
  private String createdBy;

  @Column(name = "created_date")
  private Timestamp createdDate;


}
