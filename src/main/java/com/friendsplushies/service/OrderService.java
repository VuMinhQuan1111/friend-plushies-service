package com.friendsplushies.service;

import com.friendsplushies.model.entity.Order;
import com.friendsplushies.model.request.OrderRequest;
import com.friendsplushies.model.response.OrderResponse;
import com.friendsplushies.util.cruds.service.IService;

import java.util.List;

public interface OrderService extends IService<OrderRequest, OrderResponse, Order> {
    Long countByUserId(Long userId);
    List<OrderResponse> searchByUserId(Long userId);
}
