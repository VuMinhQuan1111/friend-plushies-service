package com.friendsplushies.service;


import com.friendsplushies.model.entity.Category;
import com.friendsplushies.model.request.CategoryRequest;
import com.friendsplushies.model.response.CategoryResponse;
import com.friendsplushies.util.cruds.service.IService;

public interface CategoryService extends IService<CategoryRequest, CategoryResponse, Category> {
}