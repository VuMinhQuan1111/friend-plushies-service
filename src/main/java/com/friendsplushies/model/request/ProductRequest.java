package com.friendsplushies.model.request;

import java.math.BigDecimal;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author TuAD ON 3/21/2024
 */

@Getter
@Setter
public class ProductRequest {
  private Long productId;
  private String name;
  private Long categoryId;
  private Double size;
  private String imageUrl;
  private String description;
  private BigDecimal price;
}
