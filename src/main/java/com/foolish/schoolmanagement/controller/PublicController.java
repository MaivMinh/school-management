package com.foolish.schoolmanagement.controller;

import com.foolish.schoolmanagement.model.Users;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/public")
public class PublicController {
  @RequestMapping("/register")
  public String displayRegisterPage(Model model) {
    model.addAttribute("user", new Users());
    return "register";
  }

  @PostMapping("/create-user")
  public String createUser(@Valid Users user, Errors errors, Model model) {
    if (errors.hasErrors()) {
      model.addAttribute("errors", errors.getAllErrors());
      model.addAttribute("user", user); // Trả về cho người dùng thông tin họ vừa tạo.
      return "register";
    }
    // Tạo Users mới vào database.

    return "home";
  }
}
