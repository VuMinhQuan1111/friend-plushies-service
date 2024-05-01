package com.friendsplushies.model.response;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.friendsplushies.model.entity.OrderProduct;
import com.friendsplushies.model.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class OrderResponse {
    private Long id;
    private Long userId;
    private String name;
    private BigDecimal price;
    private BigDecimal discount;
    private String userAddress;
    private String userName;
    private String userPhone;
    private BigDecimal shipFee;
    private Timestamp createdDate;
    private String createdBy;
    private List<OrderProduct> orderProducts;
    private User user;
}
