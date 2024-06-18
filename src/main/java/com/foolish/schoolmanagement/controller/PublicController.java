package com.foolish.schoolmanagement.controller;

import com.foolish.schoolmanagement.model.ContactMsg;
import com.foolish.schoolmanagement.model.User;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/public")
public class PublicController {
  @RequestMapping("/register")
  public String displayRegisterPage(Model model) {
    model.addAttribute("user", new User());
    return "register";
  }

  @PostMapping("/create-user")
  public String createUser(@Valid User user, Errors errors, Model model) {
    if (errors.hasErrors()) {
      model.addAttribute("errors", errors.getAllErrors());
      model.addAttribute("user", user); // Trả về cho người dùng thông tin họ vừa tạo.
      return "register";
    }
    // Tạo User mới vào database.

    return "home";
  }
}
