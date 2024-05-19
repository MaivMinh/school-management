package com.foolish.schoolmanagement.controller;

import com.foolish.schoolmanagement.model.Contact;
import com.foolish.schoolmanagement.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/contact")
public class ContactController {

//  private final Logger log = LoggerFactory.getLogger(ContactController.class);
  private final Logger logger = LoggerFactory.getLogger(ContactController.class);
  private final ContactService service;

  @Autowired
  public ContactController(ContactService service) {
    super();
    this.service = service;
  }

  @GetMapping(value = {""})
  public String displayContactPage(Model model) {
    model.addAttribute("errors",null);
    model.addAttribute("contact", new Contact());
    // thì dữ liệu sẽ được trả về và quá trình validate sẽ bắt đầu.
    return "contact";
  }

  @PostMapping(value = "/saveMsg")
  public String saveMessage(@Valid Contact contact, Errors errors, Model model) {
    if (errors.hasErrors()) {
      log.info("Contact validation failed due to: " + errors.toString());
      model.addAttribute("errors", errors.getAllErrors());
      model.addAttribute("contact", contact);
      return "contact";
    }

    service.saveMessageDetails(contact);
    model.addAttribute("errors", null);
    return "redirect:/contact";
  }
}
