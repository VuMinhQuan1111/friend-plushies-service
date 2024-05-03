package com.friendsplushies.model.mapper;

import com.friendsplushies.model.entity.OrderCart;
import com.friendsplushies.model.request.OrderCartRequest;
import com.friendsplushies.model.response.OrderCartResponse;
import com.friendsplushies.util.cruds.mapper.IMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderCartMapper extends IMapper<OrderCartRequest, OrderCartResponse, OrderCart> {
    OrderCartMapper INSTANCE = Mappers.getMapper(OrderCartMapper.class);

    OrderCart toEntity(OrderCartRequest request);
}
