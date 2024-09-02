package com.foolish.schoolmanagement.rest;

import com.foolish.schoolmanagement.DTOs.UserDTO;
import com.foolish.schoolmanagement.mappers.UserMapper;
import com.foolish.schoolmanagement.model.User;
import com.foolish.schoolmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/user")
@RequiredArgsConstructor
public class UserRestController {
  private final UserService userService;
  private final UserMapper userMapper;

  @GetMapping(value = "")
  public ResponseEntity<UserDTO> getUser(Authentication authentication) {
    if (authentication.isAuthenticated()) {
      User user = userService.findUserByEmail(authentication.getName());
      if (user != null && user.getUserId() > 0) {
        // exist.
        UserDTO userDTO = userMapper.toUserDTO(user);
        return ResponseEntity.status(200).body(userDTO);
      }
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
  }
}
