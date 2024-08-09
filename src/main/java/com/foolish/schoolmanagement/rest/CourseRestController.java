package com.foolish.schoolmanagement.rest;

import com.foolish.schoolmanagement.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/courses")
@CrossOrigin(origins = "*")
public class CourseRestController {
  private final CoursesService coursesService;

  @Autowired
  public CourseRestController(CoursesService coursesService) {
    this.coursesService = coursesService;
  }
}
