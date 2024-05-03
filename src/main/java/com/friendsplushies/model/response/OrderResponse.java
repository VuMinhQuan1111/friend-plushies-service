package com.friendsplushies.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.friendsplushies.model.entity.OrderCart;
import com.friendsplushies.model.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class OrderResponse {
    private Long id;
    private String userAddress;
    private String userName;
    private String userPhone;
    private String status;
    private Timestamp createdDate;
    private String createdBy;
    private List<OrderCartResponse> orderCarts;
}
