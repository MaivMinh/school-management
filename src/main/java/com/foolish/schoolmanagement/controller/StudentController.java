package com.foolish.schoolmanagement.controller;

import com.foolish.schoolmanagement.model.Courses;
import com.foolish.schoolmanagement.model.User;
import com.foolish.schoolmanagement.service.CoursesService;
import com.foolish.schoolmanagement.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Array;
import java.util.*;

@Controller
@RequestMapping("/student")
public class StudentController {
  private final CoursesService coursesService;
  private final UserService userService;
  private final Environment environment;

  @Autowired
  public StudentController(CoursesService coursesService, UserService userService, Environment environment) {
    super();
    this.coursesService = coursesService;
    this.userService = userService;
    this.environment = environment;
  }

  @GetMapping("courses")
  public String displayCourses(Model model, Authentication authentication, @RequestParam(value = "page", required = false) String page, @RequestParam(value = "pageSize", required = false) String pageSize, @RequestParam(value = "sortField", required = false) String sortField, @RequestParam(value = "sortDir", required = false) String sortDir) {
    User user = userService.findUserByEmail(authentication.getPrincipal().toString());
    if (user == null || user.getUserId() <= 0) {
      model.addAttribute("message", "Failed to retrieve user");
      return "error";
    }

    String ePage = environment.getProperty("page");
    String ePageSize = environment.getProperty("pageSize");
    int pageNum = Integer.parseInt((page != null) ? page : ePage);
    int pageSizeNum = Integer.parseInt((pageSize != null) ? pageSize : ePageSize);
    String field = sortField != null ? sortField : "courseId";
    String dir = (sortDir != null ? sortDir : "asc");
    String reverseDir = (dir.equalsIgnoreCase("asc") ? "desc" : "asc");


    return "courses_enrolled";
  }
}
