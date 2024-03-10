package com.crms.model.mapper;

import com.crms.model.entity.User;
import com.crms.model.request.user.UserRequest;
import com.crms.model.response.UserResponse;
import com.crms.util.cruds.mapper.IMapper;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.util.StringUtils;

/**
 * Author: chautn on 4/9/2019 3:13 PM
 */
@Mapper
public interface UserMapper extends IMapper<UserRequest, UserResponse, User> {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  @Mappings(
      @Mapping(target = "name", expression = "java(generateName(request))")
  )
  User toEntity(UserRequest request);

  @Named("generateName")
  default String generateName(UserRequest request) {
    List name = new ArrayList();
    if (!StringUtils.isEmpty(request.getFirstName())) {
      name.add(request.getFirstName());
    }
    if (!StringUtils.isEmpty(request.getLastName())) {
      name.add(request.getLastName());
    }
    return String.join(" ", name);
  }
}
