package com.foolish.schoolmanagement.controller;

import com.foolish.schoolmanagement.model.Courses;
import com.foolish.schoolmanagement.model.Teach;
import com.foolish.schoolmanagement.model.User;
import com.foolish.schoolmanagement.service.CoursesService;
import com.foolish.schoolmanagement.service.TeachService;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/courses")
public class CourseController {

  private final CoursesService service;
  private final CoursesService coursesService;
  private final TeachService teachService;

  @Autowired
  public CourseController(CoursesService service, CoursesService coursesService, TeachService teachService) {
    this.service = service;
    this.coursesService = coursesService;
    this.teachService = teachService;
  }

  @GetMapping(value = {""})
  public String displayCourses(Model model, @RequestParam(value = "page", required = false) String page, @RequestParam(value = "pageSize", required = false) String pageSize, @RequestParam(value = "sortDir", required = false) String sortDir, @RequestParam(value = "sortField", required = false) String sortField) {

    int pageNum = Integer.parseInt(page != null ? page : "1");
    int pageSizeNum = Integer.parseInt(pageSize != null ? pageSize: "6");
    String dir = sortDir != null ? sortDir: "asc";
    String field = sortField != null ? sortField: "courseId";
    Page<Courses> result = coursesService.displayCourses(pageNum, pageSizeNum, field, dir);
    model.addAttribute("courses", result.getContent());
    model.addAttribute("page", result.getNumber() + 1);
    model.addAttribute("totalPages", result.getTotalPages());
    model.addAttribute("sortDir", dir);
    model.addAttribute("sortField", field);
    return "courses_user";
  }

  @GetMapping(value = "/{courseId}")
  public String displayDetailedCourse(Model model, @PathVariable(value = "courseId") String courseId, Authentication authentication, @RequestParam(value = "update", required = false) String update) {
    int id = Integer.parseInt(courseId);
    Courses course = service.findByCourseId(id);
    if (course != null && course.getCourseId() > 0) {
      model.addAttribute("course", course);
      List<Teach> list = teachService.findAllByCourses(course);
      List<User> lectures = list.stream().map(Teach::getLecture).toList();
      model.addAttribute("lectures", lectures);
    } else {
      model.addAttribute("message", "Course ID Not Found!");
      return "error";
    }
    if (update != null && update.equals("true"))   model.addAttribute("update", true);
    if (update != null && update.equals("false"))   model.addAttribute("update", false);
    if (authentication != null && authentication.getAuthorities().stream().toArray()[0].toString().equals("ROLE_ADMIN"))  {
      return "course_detail_admin";
    }
    return "course_detail_user";
  }
}
