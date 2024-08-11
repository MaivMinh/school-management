package com.foolish.schoolmanagement.controller;

import com.foolish.schoolmanagement.service.RegistrationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RegistrationsController {
  private final RegistrationsService registrationsService;

  @Autowired
  public RegistrationsController(RegistrationsService registrationsService) {
    this.registrationsService = registrationsService;
  }
}
