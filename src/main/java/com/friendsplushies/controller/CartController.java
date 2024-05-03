package com.friendsplushies.controller;

import com.friendsplushies.constant.ServicePath;
import com.friendsplushies.model.entity.Cart;
import com.friendsplushies.model.request.CartRequest;
import com.friendsplushies.model.response.CartResponse;
import com.friendsplushies.model.response.RestResult;
import com.friendsplushies.service.CartService;
import com.friendsplushies.util.cruds.controller.AbstractController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/deleteAllByProductId")
    public ResponseEntity deleteAllByProductId(@RequestBody Long productId) {
        cartService.deleteAllByProductId(productId);
        return new ResponseEntity(
                new RestResult<CartResponse>()
                        .status(RestResult.STATUS_SUCCESS)
                        .data(null),
                HttpStatus.OK);
    }

    @PostMapping("/deleteAllByUserId")
    public ResponseEntity deleteAllByUserId(@RequestBody Long userId) {
        cartService.deleteAllByUserId(userId);
        return new ResponseEntity(
                new RestResult<CartResponse>()
                        .status(RestResult.STATUS_SUCCESS)
                        .data(null),
                HttpStatus.OK);
    }
}
