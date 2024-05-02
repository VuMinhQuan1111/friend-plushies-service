package com.friendsplushies.model.request;

import java.math.BigDecimal;
import lombok.Data;
import com.friendsplushies.model.entity.Product;

@Data
public class CartRequest {
    private Long id;
    private String name;
    private BigDecimal price;
    private Long quantity;
    // private Timestamp createdDate;
    // private String createdBy;

    private Product product;
}
