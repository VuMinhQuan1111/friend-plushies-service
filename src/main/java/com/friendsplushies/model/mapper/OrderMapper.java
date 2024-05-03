package com.friendsplushies.model.mapper;

import com.friendsplushies.model.entity.Order;
import com.friendsplushies.model.request.OrderRequest;
import com.friendsplushies.model.response.OrderResponse;
import com.friendsplushies.util.cruds.mapper.IMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {OrderCartMapper.class})
public interface OrderMapper extends IMapper<OrderRequest, OrderResponse, Order> {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order toEntity(OrderRequest request);
}
