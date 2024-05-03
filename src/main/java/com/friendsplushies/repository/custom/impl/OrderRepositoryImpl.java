package com.friendsplushies.repository.custom.impl;

import com.friendsplushies.model.entity.Category;
import com.friendsplushies.model.entity.Order;
import com.friendsplushies.repository.custom.OrderRepositoryCustom;
import com.friendsplushies.util.cruds.repository.impl.AbstractRepositoryImpl;

public class OrderRepositoryImpl extends AbstractRepositoryImpl<Order> implements OrderRepositoryCustom {
    public OrderRepositoryImpl() {
        super(Order.class);
    }
}
