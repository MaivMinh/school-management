package com.foolish.schoolmanagement.controller;

import com.foolish.schoolmanagement.model.User;
import com.foolish.schoolmanagement.service.CoursesService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {
  private final CoursesService coursesService;

  @Autowired
  public StudentController(CoursesService coursesService) {
    super();
    this.coursesService = coursesService;
  }

  @GetMapping("courses")
  public String displayCourses(Model model, HttpSession session) {
    User user = (User) session.getAttribute("user");
    model.addAttribute("user", user);
    return "courses_enrolled";
  }
}
