package com.friendsplushies.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.friendsplushies.model.entity.Cart;
import com.friendsplushies.model.entity.Category;
import com.friendsplushies.model.entity.Product;
import com.friendsplushies.model.mapper.CartMapper;
import com.friendsplushies.model.mapper.CategoryMapper;
import com.friendsplushies.model.request.CartRequest;
import com.friendsplushies.model.request.ProductRequest;
import com.friendsplushies.model.response.CartResponse;

import com.friendsplushies.repository.CartRepository;
import com.friendsplushies.repository.CategoryRepository;
import com.friendsplushies.service.CartService;
import com.friendsplushies.util.cruds.service.impl.AbstractServiceImpl;

@Service
public class CartServiceImpl extends AbstractServiceImpl<CartRequest, CartResponse, Cart>
implements CartService {
    @Autowired
    private CartRepository cartRepository;

    public CartServiceImpl() {
    }

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        // super(cartRepository, CartMapper.INSTANCE, Cart.class);
        
        
    }
}
