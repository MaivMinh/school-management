package com.foolish.schoolmanagement.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/cart")
public class CartController {

  @GetMapping(value = "")
  public String displayCart(Model model) {
    return "cart";
  }
}
