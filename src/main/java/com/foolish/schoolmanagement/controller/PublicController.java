package com.foolish.schoolmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/public")
public class PublicController {
  @RequestMapping("/register")
  public String displayRegisterPage(Model model) {
    return "register";
  }
}
