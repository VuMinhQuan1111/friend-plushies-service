package com.friendsplushies.model.response;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CartResponse {

    private Long cartId;
    private Long userId;
    private Long productId;
    private ProductResponse product;
    private UserResponse user;
    private Timestamp createdDate;
    private String createdBy;
    private String status;
}
