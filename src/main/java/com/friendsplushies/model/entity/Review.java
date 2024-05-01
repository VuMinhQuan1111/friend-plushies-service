package com.friendsplushies.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.friendsplushies.model.entity.listener.FEntity;
import com.friendsplushies.model.entity.listener.FEntityListener;

import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "review")
@EntityListeners(FEntityListener.class)
public class Review implements Serializable {
    
    @Id
    @SequenceGenerator(name = "reviewGenerator", sequenceName = "review_review_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "reviewGenerator")
    @Column(name = "review_id")
    private Long reviewId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "review_description")
    private String reviewDescription;

    @Column(name = "review_time")
    private Timestamp reviewTime;
}
