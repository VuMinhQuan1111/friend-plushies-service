package com.friendsplushies.service.impl;

import com.friendsplushies.model.entity.Order;
import com.friendsplushies.model.mapper.OrderMapper;
import com.friendsplushies.model.request.OrderRequest;
import com.friendsplushies.model.response.OrderResponse;
import com.friendsplushies.repository.OrderRepository;
import com.friendsplushies.service.OrderService;
import com.friendsplushies.util.cruds.service.impl.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class OrderServiceImpl extends AbstractServiceImpl<OrderRequest, OrderResponse, Order> implements OrderService {
    public OrderServiceImpl() {
    }

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        super(orderRepository, OrderMapper.INSTANCE, Order.class);
    }

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderResponse create(OrderRequest request) {
        Order order = OrderMapper.INSTANCE.toEntity(request);
        OrderResponse response = OrderMapper.INSTANCE.toResponse(orderRepository.save(order));
        if (!CollectionUtils.isEmpty(request.getCartIds())) {
            request.getCartIds().forEach(id -> orderRepository.assignOrder(response.getId(), id));
        }
        return response;
    }

    @Override
    public List<OrderResponse> searchByUserId(Long userId) {
        return OrderMapper.INSTANCE.toResponses(orderRepository.searchByUserId(userId));
    }

    @Override
    public Long countByUserId(Long userId) {
        return orderRepository.countByUserId(userId);
    }
}
