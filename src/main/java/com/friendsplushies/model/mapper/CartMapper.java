package com.friendsplushies.model.mapper;

import com.friendsplushies.model.entity.Cart;
import com.friendsplushies.model.request.CartRequest;
import com.friendsplushies.model.response.CartResponse;
import com.friendsplushies.util.cruds.mapper.IMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartMapper extends IMapper<CartRequest, CartResponse, Cart> {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    Cart toEntity(CartRequest request);
}
