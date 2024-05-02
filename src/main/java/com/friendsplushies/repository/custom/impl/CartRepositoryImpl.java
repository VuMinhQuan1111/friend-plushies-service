package com.friendsplushies.repository.custom.impl;

import com.friendsplushies.model.entity.Cart;
import com.friendsplushies.model.entity.Category;
import com.friendsplushies.repository.custom.CartRepositoryCustom;
import com.friendsplushies.repository.custom.CategoryRepositoryCustom;
import com.friendsplushies.util.cruds.repository.impl.AbstractRepositoryImpl;

public class CartRepositoryImpl extends AbstractRepositoryImpl<Cart> implements CartRepositoryCustom {
    
    public CartRepositoryImpl() {
        super(Cart.class);
    }
}
