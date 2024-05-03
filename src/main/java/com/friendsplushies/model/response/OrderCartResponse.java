package com.friendsplushies.model.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.friendsplushies.model.entity.Cart;
import com.friendsplushies.model.entity.Order;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCartResponse {
    private Long orderCartId;
    private Long orderId;
    private Long cartId;
    private CartResponse cart;
}
