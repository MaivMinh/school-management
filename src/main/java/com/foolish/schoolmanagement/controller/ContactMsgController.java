package com.foolish.schoolmanagement.controller;

import com.foolish.schoolmanagement.model.ContactMsg;
import com.foolish.schoolmanagement.service.ContactMsgService;
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
  public String displayContactPage(Model model) {
    model.addAttribute("errors",null);
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
    ContactMsg result = service.saveMessageDetails(contact);
    System.out.println(result);
    model.addAttribute("errors", null);
    return "redirect:/contact";
  }


  @GetMapping(value = {"/display-msg"})
  public String displayContactMessage(@RequestParam(value = "status", required = false) String status , Model model, Authentication authentication) {
    List<ContactMsg> result = null;
    if (status != null) {
      if (status.equalsIgnoreCase("open"))  {
        result = service.findMessageWithStatus("open");
      } else if (status.equalsIgnoreCase("close"))  {
        result = service.findMessageWithStatus("close");
      }
    } else {
      result = service.findAll();
    }
    model.addAttribute("contactMsg", result);
    return "message";
  }

  @GetMapping(value = {"/close-msg"})
  public String closeContactMessage(@RequestParam(value = "contact_id", required = true) String id, Model model, Authentication auth) {
    int contact_id = Integer.parseInt(id);
    List<ContactMsg> result = service.closeContactMsg(contact_id, auth.getName());
    model.addAttribute("contactMsg", result);
    return "message";
  }

}