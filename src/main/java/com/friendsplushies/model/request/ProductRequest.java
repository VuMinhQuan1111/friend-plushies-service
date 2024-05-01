package com.friendsplushies.model.request;

import java.math.BigDecimal;
import lombok.Data;

/**
 * @author TuAD ON 3/21/2024
 */

@Data
public class ProductRequest {
  private Long id;
  private String name;
  private Long categoryId;
  private Double size;
  private String imageUrl;
  private String description;
  private BigDecimal price;
}
