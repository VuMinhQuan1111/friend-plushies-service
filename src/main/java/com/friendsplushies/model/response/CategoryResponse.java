package com.friendsplushies.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponse {
    private Long categoryId;
    private String categoryName;
    private String categoryDescription;
}
