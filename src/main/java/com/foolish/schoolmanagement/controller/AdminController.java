package com.foolish.schoolmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
  @GetMapping("display-courses")
  public String displayCourses() {
    return "courses";
  }

  @GetMapping("display-classes")
  public String displayClasses() {
    return "classes";
  }
}
