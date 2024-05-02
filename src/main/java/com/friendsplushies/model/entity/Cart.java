package com.friendsplushies.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.friendsplushies.model.entity.listener.FEntity;
import com.friendsplushies.model.entity.listener.FEntityListener;

import io.opencensus.common.Timestamp;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "cart")
@EntityListeners(FEntityListener.class)
public class Cart implements Serializable {

    @Id
    @SequenceGenerator(name = "cartGenerator", sequenceName = "cart_cart_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "cartGenerator")
    @Column(name = "cart_id")
    private Long id;
    private String name;
    private BigDecimal price;
    private Long quantity;
    // private Timestamp createdDate;
    // private String createdBy;

    private Product product;
}
