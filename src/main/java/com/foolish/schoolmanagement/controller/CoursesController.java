package com.foolish.schoolmanagement.controller;

import com.foolish.schoolmanagement.DTOs.CourseDTO;
import com.foolish.schoolmanagement.model.*;
import com.foolish.schoolmanagement.service.*;
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
public class CoursesController {

  private final CoursesService coursesService;
  private final TeachService teachService;
  private final UserService userService;
  private final RegistrationsService registrationsService;
  private final VideoService videoService;

  @Autowired
  public CoursesController(CoursesService coursesService, TeachService teachService, UserService userService, RegistrationsService registrationsService, VideoService videoService) {
    this.coursesService = coursesService;
    this.teachService = teachService;
    this.userService = userService;
    this.registrationsService = registrationsService;
    this.videoService = videoService;
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
    Courses course = coursesService.findByCourseId(id);
    List<Teach> list = null;
    List<User> lectures = null;
    if (course != null && course.getCourseId() > 0) {
      model.addAttribute("course", course);
      list = teachService.findAllByCourses(course);
      lectures = list.stream().map(Teach::getLecture).toList();
      model.addAttribute("lectures", lectures);
    } else {
      model.addAttribute("message", "Course ID Not Found!");
      return "error";
    }
    if (update != null && update.equals("true"))   model.addAttribute("update", true);
    if (update != null && update.equals("false"))   model.addAttribute("update", false);
    if (authentication != null && authentication.getAuthorities().stream().toArray()[0].toString().equals("ROLE_ADMIN"))  {
      CourseDTO updateCourse = new CourseDTO();
      updateCourse.setCourseId(course.getCourseId());
      updateCourse.setName(course.getName());
      updateCourse.setBegin(course.getBegin());
      updateCourse.setEnd(course.getEnd());
      updateCourse.setCapacity(course.getCapacity());
      updateCourse.setCategory(course.getCategory());
      updateCourse.setDescription(course.getDescription());
      updateCourse.setIntroduction(course.getIntroduction());
      updateCourse.setImg(course.getImg());
      updateCourse.setVote(course.getVote());
      updateCourse.setLessons(course.getLessons());
      model.addAttribute("course", updateCourse);
      return "course_detail_admin";
    }
    // Kiểm tra xem user đã đăng kí khoá học này chưa.
    User user = userService.findUserByEmail(authentication.getName());
    if (user != null && user.getUserId() > 0) {
      Registrations registrations = registrationsService.findAllByCoursesAndUser(course, user);
      if (registrations == null || registrations.getId() <= 0) {
        // Sinh viên chưa đăng kí khoá học này. Hiển thị trang để user có thể thực hiện Add to cart, Add to favourite or Register now.
        return "course_detail_user";
      } else {
        // User đã đăng kí khoá học này. Hiển thị trang web để họ có thể thực hiện xem khoá học, xem các khoá học liên quan... Comment.
        List<Video> videos = videoService.findAllByCourses(course);
        String category = course.getCategory();
        Page<Courses> result = coursesService.findAllByCategory(category, 1, 10);
        List<Courses> relateCourses = result.getContent();
        model.addAttribute("relatedCourses", relateCourses);
        model.addAttribute("videos", videos);
        return "course_attended_user";
      }
    }
    model.addAttribute("message", "Internal Server Error!");
    return "error";
  }
}
