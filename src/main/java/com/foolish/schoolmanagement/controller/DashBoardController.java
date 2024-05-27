package com.foolish.schoolmanagement.controller;

import com.foolish.schoolmanagement.model.ContactMsg;
import com.foolish.schoolmanagement.service.ContactMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashBoardController {

  @GetMapping("")
  public String displayDashboard(Model model, Authentication authentication) {
    model.addAttribute("username", authentication.getName());
    model.addAttribute("roles", authentication.getAuthorities().toString());
    return "dashboard";
  }
}
