package com.foolish.schoolmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

  @GetMapping(value = {"/login"})
  public String displayLoginPage(@RequestParam(value = "error", required = false) String error,
                                 @RequestParam(value = "logout", required = false) String logout, Model model) {
    if (error!= null) {
      model.addAttribute("error", "Username or Password is incorrect!");
    }
    else if (logout!= null) {
      model.addAttribute("message", "You have been logged out successfully!");
    }
    return "login";
  }
}
