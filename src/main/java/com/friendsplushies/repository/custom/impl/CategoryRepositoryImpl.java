package com.friendsplushies.repository.custom.impl;

import com.friendsplushies.model.entity.Category;
import com.friendsplushies.repository.custom.CategoryRepositoryCustom;
import com.friendsplushies.util.cruds.repository.impl.AbstractRepositoryImpl;

public class CategoryRepositoryImpl extends AbstractRepositoryImpl<Category> implements CategoryRepositoryCustom {

    public CategoryRepositoryImpl() {
        super(Category.class);
    }
}
