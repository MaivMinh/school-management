package com.foolish.schoolmanagement.controller;

import com.foolish.schoolmanagement.entity.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/contact")
public class ContactController {

  private final Logger log = LoggerFactory.getLogger(ContactController.class);

  @GetMapping(value = {""})
  public String displayContactPage() {
    return "contact";
  }

  @PostMapping(value = "/saveMsg")
  public ModelAndView saveMessage(Contact contact) {

    log.info("Name: " + contact.getName());
    log.info("MobileNum: " + contact.getMobileNum());
    log.info("Email: " + contact.getEmail());
    log.info("Subject: " + contact.getSubject());
    log.info("Message: " + contact.getMessage());
    return  new ModelAndView("redirect:/contact");
  }
}
