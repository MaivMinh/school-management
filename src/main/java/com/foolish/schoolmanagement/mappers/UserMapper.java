package com.foolish.schoolmanagement.mappers;

import com.foolish.schoolmanagement.DTOs.UserDTO;
import com.foolish.schoolmanagement.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
  @Mapping(source = "userId", target = "userId")
  @Mapping(source = "name", target = "name")
  @Mapping(source = "mobileNum", target = "mobileNum")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "img", target = "img")
  @Mapping(source = "roles", target = "roles")
  UserDTO toUserDTO(User user);

  @Mapping(source = "userId", target = "userId")
  @Mapping(source = "name", target = "name")
  @Mapping(source = "mobileNum", target = "mobileNum")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "img", target = "img")
  @Mapping(source = "roles", target = "roles")
  User toUser(UserDTO userDTO);
}
