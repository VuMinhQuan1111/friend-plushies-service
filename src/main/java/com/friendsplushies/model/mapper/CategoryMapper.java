package com.friendsplushies.model.mapper;

import com.friendsplushies.model.entity.Category;
import com.friendsplushies.model.request.CategoryRequest;
import com.friendsplushies.model.response.CategoryResponse;
import com.friendsplushies.util.cruds.mapper.IMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper extends IMapper<CategoryRequest, CategoryResponse, Category> {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category toEntity(CategoryRequest request);
}
