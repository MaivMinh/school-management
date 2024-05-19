package com.foolish.schoolmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/courses")
public class CourseController {

  @GetMapping(value = {""})
  public String displayCourses() {
    return "courses";
  }
}
