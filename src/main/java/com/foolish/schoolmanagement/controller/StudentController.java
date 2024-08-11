package com.foolish.schoolmanagement.controller;

import com.foolish.schoolmanagement.DTOs.RegistrationsDTO;
import com.foolish.schoolmanagement.mappers.RegistrationsMapper;
import com.foolish.schoolmanagement.model.Courses;
import com.foolish.schoolmanagement.model.Registrations;
import com.foolish.schoolmanagement.model.User;
import com.foolish.schoolmanagement.service.CoursesService;
import com.foolish.schoolmanagement.service.RegistrationsService;
import com.foolish.schoolmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/student")
public class StudentController {
  private final CoursesService coursesService;
  private final RegistrationsService registrationsService;
  private final UserService userService;
  private final Environment environment;

  @Autowired
  public StudentController(CoursesService coursesService, UserService userService, RegistrationsService registrationsService, Environment environment) {
    super();
    this.coursesService = coursesService;
    this.userService = userService;
    this.registrationsService = registrationsService;
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
    String field = sortField != null ? sortField : "id";
    String dir = (sortDir != null ? sortDir : "asc");
    String reverseDir = (dir.equalsIgnoreCase("asc") ? "desc" : "asc");

    Page<Registrations> result = registrationsService.findAllByUser(user, pageNum, pageSizeNum, field, dir);
    List<Registrations> registrations = result.getContent();
    List<RegistrationsDTO> courses = registrations.stream().map(RegistrationsMapper::convertToDTO).toList();
    model.addAttribute("page", pageNum);
    model.addAttribute("pageSize", pageSizeNum);
    model.addAttribute("totalPages", result.getTotalPages());
    model.addAttribute("field", field);
    model.addAttribute("dir", dir);
    model.addAttribute("reverseDir", reverseDir);
    model.addAttribute("courses", courses);


    return "courses_enrolled";
  }
}
