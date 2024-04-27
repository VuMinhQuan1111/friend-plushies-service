package com.friendsplushies.repository.custom.impl;

import com.friendsplushies.model.entity.Product;
import com.friendsplushies.repository.custom.ProductRepositoryCustom;
import com.friendsplushies.util.cruds.repository.impl.AbstractRepositoryImpl;

public class ProductRepositoryImpl extends AbstractRepositoryImpl<Product> implements ProductRepositoryCustom {
    public ProductRepositoryImpl() {
        super(Product.class);
    }
}
