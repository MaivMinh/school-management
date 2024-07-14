package com.foolish.schoolmanagement.controller;

import com.foolish.schoolmanagement.model.User;
import com.foolish.schoolmanagement.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/public")
public class PublicController {
  private final UserService userService;

  public PublicController(UserService service) {
    this.userService = service;
  }

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
    boolean result = userService.createNewUser(user);
    if (result) {
      return "redirect:/public/login?register=true";
    }
    return "redirect:/public/register";
  }

  @PostMapping("search")
  public String searching() {
    return "redirect:/";
  }
}
