package com.friendsplushies.service.impl;

import com.friendsplushies.model.entity.Category;
import com.friendsplushies.model.entity.Product;
import com.friendsplushies.model.mapper.CategoryMapper;
import com.friendsplushies.model.mapper.ProductMapper;
import com.friendsplushies.model.request.CategoryRequest;
import com.friendsplushies.model.response.CategoryResponse;
import com.friendsplushies.repository.CategoryRepository;
import com.friendsplushies.repository.ProductRepository;
import com.friendsplushies.service.CategoryService;
import com.friendsplushies.util.cruds.service.impl.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends AbstractServiceImpl<CategoryRequest, CategoryResponse, Category> implements CategoryService {
    public CategoryServiceImpl() {
    }

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        super(categoryRepository, CategoryMapper.INSTANCE, Category.class);
    }
}
