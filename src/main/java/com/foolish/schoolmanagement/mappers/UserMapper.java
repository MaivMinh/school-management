package com.foolish.schoolmanagement.mappers;

import com.foolish.schoolmanagement.DTOs.UserDTO;
import com.foolish.schoolmanagement.model.User;
import jakarta.validation.Valid;

import java.util.List;

public class UserMapper {
  public static UserDTO convertToDTO(@Valid User user) {
    return new UserDTO(user.getUserId(), user.getName(), user.getMobileNum(), user.getEmail(), user.getImg(), null, user.getRoles(), user.getPassioClass().getClassId(), user.getPassioClass().getName());
  }

  public static User convertToUser(UserDTO userDTO) {
    User user = new User();
    user.setUserId(userDTO.userId);
    user.setName(userDTO.name);
    user.setMobileNum(userDTO.mobileNum);
    user.setEmail(userDTO.email);
    user.setImg(userDTO.img);
    user.setRoles(userDTO.roles);
    return user;
  }
}
