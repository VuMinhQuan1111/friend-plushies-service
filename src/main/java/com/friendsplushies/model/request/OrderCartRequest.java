package com.friendsplushies.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCartRequest {
    private Long orderCartId;
    private Long cartId;
    private Long orderId;
}
