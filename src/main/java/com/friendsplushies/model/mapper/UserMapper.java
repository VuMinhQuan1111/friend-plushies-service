package com.friendsplushies.model.mapper;

import com.friendsplushies.model.entity.User;
import com.friendsplushies.model.request.user.UserRequest;
import com.friendsplushies.model.response.UserResponse;
import com.friendsplushies.util.cruds.mapper.IMapper;
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
  User toEntity(UserRequest request);
}
