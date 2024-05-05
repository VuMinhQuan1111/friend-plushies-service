package com.friendsplushies.service.impl;

import com.friendsplushies.model.entity.Cart;
import com.friendsplushies.model.entity.Order;
import com.friendsplushies.model.entity.OrderCart;
import com.friendsplushies.model.entity.Product;
import com.friendsplushies.model.mapper.ProductMapper;
import com.friendsplushies.model.request.ProductRequest;
import com.friendsplushies.model.response.ProductResponse;
import com.friendsplushies.repository.CartRepository;
import com.friendsplushies.repository.OrderRepository;
import com.friendsplushies.repository.ProductRepository;
import com.friendsplushies.service.ProductService;
import com.friendsplushies.util.cruds.service.impl.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl extends AbstractServiceImpl< ProductRequest, ProductResponse, Product>
implements ProductService {
    public ProductServiceImpl() {
    }

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        super(productRepository, ProductMapper.INSTANCE, Product.class);
    }
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;
    @Override
    public boolean delete(Long id) {
        List<Order> orders = orderRepository.getAllByProductId(id);
        List<Cart> carts = cartRepository.getAllByProductId(id);
        if (!CollectionUtils.isEmpty(orders)) {
            orderRepository.deleteAllOrderCartByOrderIds(orders.stream().map(Order::getId).collect(Collectors.toList()));
            orderRepository.deleteAll(orders);
        }
        if (!CollectionUtils.isEmpty(carts)) {
            cartRepository.deleteAll(carts);
        }
        return super.delete(id);
    }
}
