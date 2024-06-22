package com.foolish.schoolmanagement.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {

  @GetMapping(value = {"/public/login"})
  public String displayLoginPage(@RequestParam(value = "error", required = false) String error,
                                 @RequestParam(value = "logout", required = false) String logout,
                                 @RequestParam(value = "register", required = false) String register, Model model) {
    if (error!= null) {
      model.addAttribute("error", "Username or Password is incorrect!");
    }
    else if (logout!= null) {
      model.addAttribute("message", "You have been logged out successfully!");
    } else if(register != null) {
      model.addAttribute("register", "You have been registered successfully!");
    }
    return "login";
  }

  @GetMapping(value = "/change-password")
  public String displayChangePasswordPage(Model model) {
    return "login";
  }
}
