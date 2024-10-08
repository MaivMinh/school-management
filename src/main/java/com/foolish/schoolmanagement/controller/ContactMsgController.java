package com.foolish.schoolmanagement.controller;

import com.foolish.schoolmanagement.model.ContactMsg;
import com.foolish.schoolmanagement.service.ContactMsgService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
public class ContactMsgController {

  private final Logger logger = LoggerFactory.getLogger(ContactMsgController.class);
  private final ContactMsgService service;


  @Autowired
  public ContactMsgController(ContactMsgService service) {
    super();
    this.service = service;
  }

  @GetMapping(value = {"/contact"})
  public String displayContactPage(@RequestParam(name = "success", required = false) boolean success, Model model, HttpSession httpSession) {
    if (success)  {
      model.addAttribute("message", "You submitted successfully!");
      model.addAttribute("contact", new ContactMsg());
      return "contact";
    }
    model.addAttribute("errors", null);
    model.addAttribute("contact", new ContactMsg());
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    model.addAttribute("username", auth.getName());
    // thì dữ liệu sẽ được trả về và quá trình validate sẽ bắt đầu.
    return "contact";
  }

  @PostMapping(value = "/save-msg")
  public String saveMessage(@Valid ContactMsg contact, Errors errors, Model model) {
    if (errors.hasErrors()) {
      log.info("ContactMsg validation failed due to: " + errors.toString());
      model.addAttribute("errors", errors.getAllErrors());
      model.addAttribute("contact", contact);
      return "contact";
    }
    ContactMsg result = service.save(contact);
    model.addAttribute("errors", null);
    return "redirect:/contact?success=true";
  }
}
