package com.foolish.schoolmanagement.controller;

import com.foolish.schoolmanagement.model.*;
import com.foolish.schoolmanagement.service.*;
import jakarta.validation.Validator;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Slf4j
@Controller
@Data
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {
  private final ClassService classService;
  private final UserService userService;
  private final CoursesService coursesService;
  private final ContactMsgService contactMsgService;
  private final Environment environment;
  private final CloudinaryService cloudinaryService;
  private final Validator validator;
  private final RegistrationsService registrationsService;

  @Autowired
  public AdminController(ClassService classService, UserService userService, CoursesService coursesService, ContactMsgService contactMsgService, RegistrationsService registrationsService, Environment environment, CloudinaryService cloudinaryService, Validator validator) {
    super();
    this.classService = classService;
    this.userService = userService;
    this.coursesService = coursesService;
    this.contactMsgService = contactMsgService;
    this.environment = environment;
    this.cloudinaryService = cloudinaryService;
    this.registrationsService = registrationsService;
    this.validator = validator;
  }

  @GetMapping("classes")
  public String displayClasses(Model model, @RequestParam(value = "success", required = false) String success, @RequestParam(value = "existed", required = false) String existed, @RequestParam(value = "page", required = false) String page, @RequestParam(value = "pageSize", required = false) String pageSize, @RequestParam(value = "sortDir", required = false) String sortDir, @RequestParam(value = "sortField", required = false) String sortField) {

    int pageNum = Integer.parseInt((page != null) ? page : environment.getProperty("page"));
    int pageSizeNum = Integer.parseInt((pageSize != null) ? pageSize : environment.getProperty("pageSize"));
    String dir = (sortDir != null) ? sortDir : "asc";
    String reverseDir = (dir.equalsIgnoreCase("asc") ? "desc" : "asc");
    String field = (sortField != null) ? sortField : "classId";
    Page<PassioClass> result = classService.findAll(pageNum, pageSizeNum, field, dir);

    model.addAttribute("classes", result.getContent());
    model.addAttribute("page", pageNum);
    model.addAttribute("pageSize", pageSizeNum);
    model.addAttribute("totalPages", result.getTotalPages());
    model.addAttribute("sortDir", dir);
    model.addAttribute("sortField", field);
    model.addAttribute("reverseSortDir", reverseDir);

    if (success != null && success.equalsIgnoreCase("true")) {
      model.addAttribute("success", true);
    } else if (success != null && success.equalsIgnoreCase("false")) {
      model.addAttribute("success", false);
    } else if (existed != null && existed.equalsIgnoreCase("true")) {
      model.addAttribute("existed", true);
    }
    return "classes";
  }

  @GetMapping("classes/{classId}")
  public String displayStudentInClassWithClassId(Model model, @PathVariable("classId") String classId, @RequestParam(value = "success", required = false) String success, @RequestParam(value = "error", required = false) String error, @RequestParam(value = "deleted", required = false) String deleted, @RequestParam(value = "page", required = false) String page, @RequestParam(value = "pageSize", required = false) String pageSize, @RequestParam(value = "sortDir", required = false) String sortDir, @RequestParam(value = "sortField", required = false) String sortField) {

    int pageNum = Integer.parseInt((page != null ? page : environment.getProperty("page")));
    int pageSizeNum = Integer.parseInt((pageSize != null ? pageSize : environment.getProperty("pageSize")));
    String dir = (sortDir != null ? sortDir : "asc");
    String reverseDir = (dir.equalsIgnoreCase("asc") ? "desc" : "asc");
    String field = (sortField != null ? sortField : "name");

    PassioClass passioClass = classService.findByClassId(Integer.parseInt(classId));
    if (passioClass == null || passioClass.getClassId() <= 0) {
      return "error";
    }
    Page<User> result = userService.findUsersByPassioClass(passioClass, pageNum, pageSizeNum, dir, field);
    System.out.println("A number of student = " + result.getContent().size());
    model.addAttribute("passioClass", passioClass);
    model.addAttribute("students", result.getContent());
    model.addAttribute("page", pageNum);
    model.addAttribute("pageSize", pageSizeNum);
    model.addAttribute("totalPages", result.getTotalPages());
    model.addAttribute("sortField", field);
    model.addAttribute("sortDir", dir);
    model.addAttribute("reverseSortDir", reverseDir);

    if (success != null && success.equalsIgnoreCase("true")) {
      model.addAttribute("success", true);
    }
    if (error != null && error.equalsIgnoreCase("true")) {
      model.addAttribute("error", true);
    }
    if (deleted != null) {
      if (deleted.equalsIgnoreCase("true"))
        model.addAttribute("deleted", true);
      else model.addAttribute("deleted", false);
    }
    return "students";
  }

  @PostMapping("create-new-class")
  public String createNewClass(Model model, PassioClass instance) {
    String className = instance.getName();
    PassioClass object = classService.findAllByName(className);
    if (object != null && object.getClassId() > 0) {
      return "redirect:/admin/classes?existed=true";
    }
    PassioClass addedInstance = classService.createNewClass(instance);
    List<PassioClass> classes = classService.findAll();
    model.addAttribute("classes", classes);
    if (addedInstance != null && addedInstance.getClassId() > 0) {
      // success.
      return "redirect:/admin/classes?success=true";
    }
    // error
    return "redirect:/admin/classes?success=false";
  }

  @PostMapping("add-student")
  public String addStudentIntoClass(Model model, User user, @RequestParam("id") String id) {
    user = userService.findUserByEmail(user.getEmail());
    if (user != null && user.getUserId() > 0 && user.getPassioClass() == null) {
      PassioClass pClass = classService.findByClassId(Integer.parseInt(id));
      if (pClass != null && pClass.getClassId() > 0) {
        pClass.getStudents().add(user); // Thêm student vào class.
        user.setPassioClass(pClass); // Thêm class vào student.
        userService.save(user);
        classService.save(pClass);
      } else return ("redirect:/admin/classes/" + id + "?error=true");
    } else return ("redirect:/admin/classes/" + id + "?error=true");
    return ("redirect:/admin/classes/" + id + "?success=true");
  }

  @PostMapping("delete-student")
  public String deleteStudentFromClass(Model model, @RequestParam("userId") String userId) {
    User user = userService.findUserByUserId(Integer.parseInt(userId));
    PassioClass pClass = user.getPassioClass();
    // Tiến hành loại bỏ thông tin ở trong student and class.
    user.setPassioClass(null);
    user = userService.save(user);
    if (user != null && user.getUserId() > 0)
      return ("redirect:/admin/classes/" + pClass.getClassId() + "?deleted=true");
    return ("redirect:/admin/classes/" + pClass.getClassId() + "?deleted=false");
  }

  @GetMapping("courses")
  public String displayCourses(Model model, @RequestParam(value = "added", required = false) String added, @RequestParam(value = "page", required = false) String page, @RequestParam(value = "pageSize", required = false) String pageSize, @RequestParam(value = "sortDir", required = false) String sortDir, @RequestParam(value = "sortField", required = false) String sortField) {
    int pageNum = Integer.parseInt((page != null) ? page : environment.getProperty("page"));
    int pageSizeNum = Integer.parseInt((pageSize != null ? pageSize : environment.getProperty("pageSize")));
    String dir = sortDir != null ? sortDir : "asc";
    String field = sortField != null ? sortField : "courseId";
    String reverseDir = dir.equalsIgnoreCase("asc") ? "desc" : "asc";

    Page<Courses> result = coursesService.displayCourses(pageNum, pageSizeNum, field, dir);
    model.addAttribute("courses", result.getContent());
    model.addAttribute("page", pageNum);
    model.addAttribute("pageSize", pageSizeNum);
    model.addAttribute("totalPages", result.getTotalPages());
    model.addAttribute("sortDir", dir);
    model.addAttribute("sortField", field);
    model.addAttribute("reverseSortDir", reverseDir);
    if (added != null) model.addAttribute("added", added.equalsIgnoreCase("true"));
    return "courses_admin";
  }

  @PostMapping("add-new-course")
  public String addNewCourse(NewCourse newCourse, Model model) {
    // Hàm thêm mới một khoá học vào danh sách.
    Courses course = new Courses();
    course.setName(newCourse.getName());
    course.setFee(newCourse.getFee());
    course.setCapacity(newCourse.getCapacity());
    course.setBegin(newCourse.getBegin());
    course.setEnd(newCourse.getEnd());
    course.setIntroduction(newCourse.getIntroduction());
    course.setDescription(newCourse.getDescription());
    course.setAttendees(0);
    course.setLessons(Integer.parseInt(newCourse.getLessons()));
    course.setCategory(newCourse.getCategory());
    User user = userService.findUserByUserId(Integer.parseInt(newCourse.getLecturer()));
    if (user == null || user.getUserId() <= 0) {
      model.addAttribute("message", "Lecturer doesn't exist !");
      return "redirect:/admin/course?added=false";
    }
    boolean isOpen = new Date().before(course.getBegin());
    if (isOpen) course.setState("OPEN");
    else course.setState("CLOSED");

    MultipartFile file = newCourse.getFile();
    if (file != null) {
      String url = cloudinaryService.uploadFile(file);
      course.setImg(url);
    } else model.addAttribute("message", "Failure to upload course image !");
    course = coursesService.save(course);
    if (course != null && course.getCourseId() > 0)
      return "redirect:/admin/courses?added=true";
    return "redirect:/admin/courses?added=false";
  }

  @PostMapping(value = "/update-course")
  public String updateCourse(NewCourse newCourse) {
    // Thực hiện cập nhật khoá học.
    int id = newCourse.getCourseId();
    Courses course = coursesService.findByCourseId(id);
    course.setName(newCourse.getName());
    course.setLessons(Integer.parseInt(newCourse.getLessons()));
    course.setVote(Double.parseDouble(newCourse.getVote()));
    course.setIntroduction(newCourse.getIntroduction());
    course.setDescription(newCourse.getDescription());

    User lecturer = userService.findUserByUserId(Integer.parseInt(newCourse.getLecturer()));
    if (lecturer == null || lecturer.getUserId() <= 0)
      return "redirect:/courses/" + id + "?update=false";
    MultipartFile file = newCourse.getFile();
    if (file != null && !file.isEmpty()) {
      String url = cloudinaryService.uploadFile(file);
      course.setImg(url);
    }
    course = coursesService.save(course);
    if (course == null || course.getCourseId() <= 0)
      return "redirect:/couses/" + id + "?update=false";
    return "redirect:/courses/" + course.getCourseId() + "?update=true";
  }

  @GetMapping("/courses/{courseId}/students")
  public String displayStudentsInCourse(Model model, @PathVariable(value = "courseId") String courseId, @RequestParam(value = "added", required = false) String added, @RequestParam(value = "enrolled", required = false) String enrolled, @RequestParam(value = "existed", required = false) String existed, @RequestParam(value = "success", required = false) String success, @RequestParam(value = "error", required = false) String error, @RequestParam(value = "page", required = false) String page, @RequestParam(value = "pageSize", required = false) String pageSize, @RequestParam(value = "sortField", required = false) String sortField, @RequestParam(value = "sortDir", required = false) String sortDir) {
    Courses courses = coursesService.findByCourseId(Integer.parseInt(courseId));

    int pageNum = Integer.parseInt(page != null ? page: environment.getProperty("page"));
    int pageSizeNum = Integer.parseInt(pageSize != null ? pageSize: environment.getProperty("pageSize"));
    String field = (sortField != null ? sortField: "id");
    String dir = (sortDir != null ? sortDir: "asc");
    Page<Registrations> result = registrationsService.findAllByCourses(courses, pageNum, pageSizeNum, field, dir);
    List<User> users = result.getContent().stream().map(Registrations::getUser).toList();
    model.addAttribute("courses", courses);
    model.addAttribute("users", users);
    model.addAttribute("page", pageNum);
    model.addAttribute("pageSize", pageSizeNum);
    model.addAttribute("totalPages", result.getTotalPages());
    model.addAttribute("sortField", field);
    model.addAttribute("sortDir", dir);
    if (added != null) model.addAttribute("added", added.equalsIgnoreCase("true"));
    if (enrolled != null) model.addAttribute("enrolled", enrolled.equalsIgnoreCase("true"));
    if (existed != null) model.addAttribute("existed", existed.equalsIgnoreCase("true"));
    if (success != null) model.addAttribute("success", success.equalsIgnoreCase("true"));
    if (error != null) model.addAttribute("error", error.equalsIgnoreCase("true"));
    return "course_students";
  }

//  @PostMapping("add-student-to-course")
//  public String addStudentToCourse(User user, @RequestParam(value = "courseId", required = true) String courseId) {
//    user = userService.findUserByEmail(user.getEmail());
//    Courses courses = coursesService.findByCourseId(Integer.parseInt(courseId));
//    if (user != null && user.getUserId() > 0) {
//      // user existed.
//      if (courses != null && courses.getCourseId() > 0) {
//        // courses existed.
//        // check number of enrolled in.
//        if (courses.getAttendees() == courses.getCapacity())
//          return "redirect:/admin/view-students?courseId=" + courseId + "&added=false";
//        if (courses.getUsers().contains(user))
//          return "redirect:/admin/view-students?courseId=" + courseId + "&enrolled=true";
//        courses.getUsers().add(user);
//        courses.setAttendees(courses.getAttendees() + 1);
//        user.getCourses().add(courses);
//        coursesService.save(courses);
//        return "redirect:/admin/courses/" + courseId + "/students" + "&added=true";
//      }
//    } else return "redirect:/admin/view-students?courseId=" + courseId + "&existed=false";
//    return "redirect:/admin/view-students?courseId=" + courseId + "&added=false";
//  }

//  @PostMapping("delete-student-from-course")
//  public String deleteStudentFromCourse(@RequestParam(value = "userId", required = true) String userId, @RequestParam(value = "courseId", required = true) String courseId) {
//    Courses courses = coursesService.findByCourseId(Integer.parseInt(courseId));
//    User user = userService.findUserByUserId(Integer.parseInt(userId));
//    if (courses != null && courses.getCourseId() > 0) {
//      // course existed.
//      if (user != null && user.getUserId() > 0) {
//        // user existed.
//        if (!courses.getUsers().contains(user) || !user.getCourses().contains(courses))
//          return "redirect:/admin/view-students?courseId=" + courseId + "&error=true";
//        courses.getUsers().remove(user);
//        user.getCourses().remove(courses);
//        coursesService.save(courses);
//        return "redirect:/admin/view-students?courseId=" + courseId + "&success=true";
//      }
//    }
//    return "redirect:/admin/view-students?courseId=" + courseId + "&error=true";
//  }


  @GetMapping(value = {"messages"})
  public String displayContactMessage(@RequestParam(value = "status", required = false) String status, Model model, @RequestParam(value = "page", required = false) String page, @RequestParam(value = "pageSize", required = false) String pageSize, @RequestParam(value = "sortField", required = false) String sortField, @RequestParam(value = "sortDir", required = false) String sortDir) {
    int pageNum = Integer.parseInt((page != null) ? (page) : (environment.getProperty("page")));
    int pageSizeNum = Integer.parseInt((pageSize != null) ? (pageSize) : environment.getProperty("pageSize"));
    String field = sortField != null ? sortField : "name";
    String dir = sortDir != null ? sortDir : "asc";
    status = (status != null) ? status : "OPEN";

    Page<ContactMsg> result = contactMsgService.findAllByStatus(pageNum, pageSizeNum, status, field, dir);

    model.addAttribute("page", result.getNumber() + 1);
    model.addAttribute("totalPages", result.getTotalPages());
    model.addAttribute("sortDir", dir);
    model.addAttribute("sortField", field);
    model.addAttribute("reverseSortDir", (dir.equalsIgnoreCase("asc") ? "desc" : "asc"));
    model.addAttribute("contactMsgs", result.getContent());
    return "message";
  }

  @GetMapping(value = {"close-message"})
  public String closeContactMessage(@RequestParam(value = "contact_id", required = true) String id, Model model) {
    int contact_id = Integer.parseInt(id);  // Có được ID của message.
    try {
      ContactMsg message = contactMsgService.findByContactID(contact_id);
      message.setStatus("CLOSED");
      contactMsgService.save(message);
    } catch (RuntimeException e) {
      throw new RuntimeException(e);
    }
    model.addAttribute("contactMsg", contactMsgService.findAll());
    return "message";
  }
}
