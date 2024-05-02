package com.friendsplushies.controller;

import org.springframework.web.bind.annotation.RestController;

import com.friendsplushies.constant.ServicePath;
import com.friendsplushies.model.entity.Cart;
import com.friendsplushies.model.entity.Category;
import com.friendsplushies.model.request.CartRequest;
import com.friendsplushies.model.request.CategoryRequest;
import com.friendsplushies.model.response.CartResponse;
import com.friendsplushies.model.response.CategoryResponse;
import com.friendsplushies.service.CartService;
import com.friendsplushies.service.CategoryService;
import com.friendsplushies.util.cruds.controller.AbstractController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(ServicePath.CART)
public class CartController extends AbstractController<CartRequest, CartResponse, Cart> {
    public static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartService cartService;

    public CartController() {
    }
    @Autowired
    public CartController(CartService cartService) {
        super(cartService);
    }
}
