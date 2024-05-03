package com.friendsplushies.model.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author TuAD ON 3/21/2024
 */
@Getter
@Setter
public class CategoryRequest {
  private Long categoryId;
  private String name;
  private String description;
}
