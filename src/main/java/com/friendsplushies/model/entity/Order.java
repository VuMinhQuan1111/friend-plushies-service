package com.friendsplushies.model.entity;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.friendsplushies.model.entity.listener.FEntity;
import com.friendsplushies.model.entity.listener.FEntityListener;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "order")
@EntityListeners(FEntityListener.class)
public class Order implements FEntity, Serializable {
  @Id
  @SequenceGenerator(name = "orderGenerator", sequenceName = "order_order_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "orderGenerator")
  @Column(name = "order_id")
  private Long orderId;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "order_name")
  private String name;

  @Column(name = "price")
  private BigDecimal price;

  @Column(name = "discount")
  private BigDecimal discount;

  @Column(name = "user_address")
  private String userAddress;

  @Column(name = "user_name")
  private String userName;

  @Column(name = "user_phone")
  private String userPhone;

  @Column(name = "ship_fee")
  private BigDecimal shipFee;

  @Column(name = "created_date")
  private Timestamp createdDate;

  @Column(name = "created_by")
  private String createdBy;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
  @JsonManagedReference
  private List<Product> products;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
  @JsonManagedReference
  private User user;
}
