package com.foolish.schoolmanagement.controller;

import com.foolish.schoolmanagement.model.User;
import com.foolish.schoolmanagement.repo.UserRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashBoardController {
  private final UserRepo userRepo;

  @Autowired
  public DashBoardController(UserRepo _userRepo) {
    super();
    this.userRepo = _userRepo;
  }

  @GetMapping("")
  public String displayDashboard(Model model, Authentication authentication, HttpSession httpSession) {
    User user = userRepo.getUserByEmail(authentication.getName());
    httpSession.setAttribute("user", user);
    model.addAttribute("email", authentication.getName());
    model.addAttribute("roles", authentication.getAuthorities().toString());
    return "dashboard";
  }
}
