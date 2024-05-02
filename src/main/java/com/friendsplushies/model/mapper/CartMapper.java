package com.friendsplushies.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.friendsplushies.model.entity.Cart;
import com.friendsplushies.model.request.CartRequest;

@Mapper
public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    Cart toEntity(CartRequest request);

    
}
