package com.friendsplushies.service.impl;

import com.friendsplushies.model.entity.Cart;
import com.friendsplushies.model.mapper.CartMapper;
import com.friendsplushies.model.request.CartRequest;
import com.friendsplushies.model.response.CartResponse;
import com.friendsplushies.repository.CartRepository;
import com.friendsplushies.service.CartService;
import com.friendsplushies.util.cruds.service.impl.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl extends AbstractServiceImpl<CartRequest, CartResponse, Cart> implements CartService {
    @Autowired
    private CartRepository cartRepository;

    public CartServiceImpl() {
    }

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        super(cartRepository, CartMapper.INSTANCE, Cart.class);
    }

    @Override
    @Transactional
    public void deleteAllByProductId(Long productId) {
        cartRepository.updateStatusInactiveByProductId(productId);
    }

    @Override
    @Transactional
    public void deleteAllByUserId(Long userId) {
        List<Cart> carts = cartRepository.findAllByUserId(userId);
        carts.forEach(cart -> {
            cart.setStatus("INACTIVE");
        });
        cartRepository.saveAll(carts);
    }
}
