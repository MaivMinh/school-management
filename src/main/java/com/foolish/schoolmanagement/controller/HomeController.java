package com.foolish.schoolmanagement.controller;

import com.foolish.schoolmanagement.model.User;
import com.foolish.schoolmanagement.repo.UserRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  @GetMapping(value = {"/", "", "/home"})
  public String displayHomePage() {
    return "home";
  }

}
