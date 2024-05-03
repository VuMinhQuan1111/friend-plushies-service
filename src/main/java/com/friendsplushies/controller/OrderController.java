package com.friendsplushies.controller;

import com.friendsplushies.constant.ServicePath;
import com.friendsplushies.model.entity.Order;
import com.friendsplushies.model.request.OrderRequest;
import com.friendsplushies.model.response.CartResponse;
import com.friendsplushies.model.response.OrderResponse;
import com.friendsplushies.model.response.RestResult;
import com.friendsplushies.service.OrderService;
import com.friendsplushies.util.cruds.controller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(ServicePath.ORDER)
public class OrderController extends AbstractController<OrderRequest, OrderResponse, Order> {
    @Autowired
    private OrderService orderService;

    public OrderController() {
    }

    @Autowired
    public OrderController(OrderService orderService) {
        super(orderService);
    }

    @PostMapping("/searchByUserId")
    public ResponseEntity<RestResult<List<OrderResponse>>> searchByUserId(@RequestBody Long userId) {
        RestResult result = new RestResult<>();
        result.message("Get order by user success");
        result.data(orderService.searchByUserId(userId));
        Map<String, String> metadata = new HashMap<>();
        Long total = orderService.countByUserId(userId);
        metadata.put("total", String.valueOf(total));
        result.metadata(metadata);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
