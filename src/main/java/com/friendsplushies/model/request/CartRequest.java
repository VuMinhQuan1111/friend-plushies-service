package com.friendsplushies.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequest {
    private Long cartId;
    private Long userId;
    private Long productId;
}
