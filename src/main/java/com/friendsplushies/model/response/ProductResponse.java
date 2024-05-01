package com.friendsplushies.model.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponse {
    private Long id;
    private String name;
    private Long categoryId;
    private Double size;
    private String imageUrl;
    private String description;
    private BigDecimal price;
    private CategoryResponse category;
}
