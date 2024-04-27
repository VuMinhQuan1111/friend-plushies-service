package com.friendsplushies.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.friendsplushies.constant.ServicePath;
import com.friendsplushies.model.entity.Order;
import com.friendsplushies.model.request.OrderRequest;
import com.friendsplushies.model.response.OrderResponse;
import com.friendsplushies.util.cruds.controller.AbstractController;


@RestController
@RequestMapping(ServicePath.ORDER)
public class OrderController extends AbstractController<OrderRequest, OrderResponse, Order> {
    
}
