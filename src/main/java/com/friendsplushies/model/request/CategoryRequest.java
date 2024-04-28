package com.friendsplushies.model.request;

import lombok.Data;

/**
 * @author TuAD ON 3/21/2024
 */
@Data
public class CategoryRequest {
  private Long categoryId;
  private String categoryName;
  private String categoryDescription;
}
