package com.friendsplushies.service;

import com.friendsplushies.model.entity.Cart;
import com.friendsplushies.model.entity.Category;
import com.friendsplushies.model.request.CartRequest;
import com.friendsplushies.model.request.CategoryRequest;
import com.friendsplushies.model.response.CartResponse;
import com.friendsplushies.model.response.CategoryResponse;
import com.friendsplushies.util.cruds.service.IService;

public interface CartService extends IService<CartRequest, CartResponse, Cart> {
    
}
