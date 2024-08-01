package com.foolish.schoolmanagement.controller;

import com.foolish.schoolmanagement.model.Courses;
import com.foolish.schoolmanagement.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/courses")
public class CourseController {

  private final CoursesService service;

  @Autowired
  public CourseController(CoursesService service) {
    this.service = service;
  }

  @GetMapping(value = {""})
  public String displayCourses() {
    return "courses_user";
  }

  @GetMapping(value = "/{courseId}")
  public String displayDetailedCourse(Model model, @PathVariable(value = "courseId") String courseId) {
    int id = Integer.parseInt(courseId);
    Courses course = service.findByCourseId(id);
    if (course != null && course.getCourseId() > 0) {
      model.addAttribute("course", course);
    } else {
      model.addAttribute("message", "Course ID Not Found!");
      return "error";
    }
    return "course_detail";
  }
}
