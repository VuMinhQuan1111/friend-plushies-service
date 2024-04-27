package com.friendsplushies.model.mapper;

import com.friendsplushies.model.entity.Product;
import com.friendsplushies.model.request.ProductRequest;
import com.friendsplushies.model.response.ProductResponse;
import com.friendsplushies.util.cruds.mapper.IMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface ProductMapper extends IMapper<ProductRequest, ProductResponse, Product> {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toEntity(ProductRequest request);
}
