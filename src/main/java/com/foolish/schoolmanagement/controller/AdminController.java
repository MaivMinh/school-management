package com.foolish.schoolmanagement.controller;

import com.foolish.schoolmanagement.DTOs.CourseDTO;
import com.foolish.schoolmanagement.mappers.CourseMapper;
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
  public String displayClasses(Model model, @RequestParam(value = "success", required = false) String success, @RequestParam(value = "existed", required = false) String existed, @RequestParam(value = "deleted", required = false) String deleted, @RequestParam(value = "page", required = false) String page, @RequestParam(value = "pageSize", required = false) String pageSize, @RequestParam(value = "sortDir", required = false) String sortDir, @RequestParam(value = "sortField", required = false) String sortField) {

    int pageNum = Integer.parseInt((page != null) ? page : environment.getProperty("page"));
    int pageSizeNum = Integer.parseInt((pageSize != null) ? pageSize : environment.getProperty("pageSize"));
    String dir = (sortDir != null) ? sortDir : "asc";
    String reverseDir = (dir.equalsIgnoreCase("asc") ? "desc" : "asc");
    String field = (sortField != null) ? sortField : "classId";
    Page<PassioClass> result = classService.findAll(pageNum, pageSizeNum, field, dir);

    model.addAttribute("classes", result.getContent());
    model.addAttribute("class", new PassioClass());
    model.addAttribute("page", pageNum);
    model.addAttribute("pageSize", pageSizeNum);
    model.addAttribute("totalPages", result.getTotalPages());
    model.addAttribute("sortDir", dir);
    model.addAttribute("sortField", field);
    model.addAttribute("reverseSortDir", reverseDir);

    if (deleted != null && deleted.equalsIgnoreCase("true")) {
      model.addAttribute("added", true);
    }
    if (success != null && success.equalsIgnoreCase("true")) {
      model.addAttribute("success", true);
    }
    if (success != null && success.equalsIgnoreCase("false")) {
      model.addAttribute("success", false);
    }
    if (existed != null && existed.equalsIgnoreCase("true")) {
      model.addAttribute("existed", true);
    }
    return "classes";
  }

  @GetMapping("classes/{classId}")
  public String displayStudentInClassWithClassId(Model model, @PathVariable("classId") String classId, @RequestParam(value = "deleted", required = false) String deleted, @RequestParam(value = "added", required = false) String added, @RequestParam(value = "page", required = false) String page, @RequestParam(value = "pageSize", required = false) String pageSize, @RequestParam(value = "sortDir", required = false) String sortDir, @RequestParam(value = "sortField", required = false) String sortField) {

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
    model.addAttribute("user", new User());

    if (added != null) {
      if (added.equalsIgnoreCase("true"))
        model.addAttribute("added", true);
      else model.addAttribute("added", false);
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

  @PostMapping("/delete-class")
  public String deleteClass(Model model, @RequestParam(value = "classId") int classId) {
    PassioClass pClass = classService.findByClassId(classId);
    if (pClass != null && pClass.getClassId() > 0) {
      // Loại bỏ mỗi quan hệ OneToMany với User(ROLE_STUDENT).
      Set<User> students = pClass.getStudents();
      students.forEach(student -> {
        student.setPassioClass(null);
        userService.save(student);
      });
      classService.delete(pClass);
    } else return "redirect:/admin/classes?deleted=false";
    return "redirect:/admin/classes?deleted=true";
  }

  @PostMapping("/classes/{classId}")
  public String addStudentIntoClass(Model model, User user, @PathVariable(name = "classId") String classId) {
    System.out.println(classId);
    user = userService.findUserByEmail(user.getEmail());
    int id = Integer.parseInt(classId);
    if (user != null && user.getUserId() > 0 && user.getPassioClass() == null) {
      PassioClass pClass = classService.findByClassId(id);
      if (pClass != null && pClass.getClassId() > 0) {
        pClass.getStudents().add(user); // Thêm student vào class.
        user.setPassioClass(pClass); // Thêm class vào student.
        userService.save(user);
        classService.save(pClass);
      } else return ("redirect:/admin/classes/" + id + "?added=false");
    } else return ("redirect:/admin/classes/" + id + "?added=false");
    return ("redirect:/admin/classes/" + id + "?added=true");
  }

  @PostMapping("delete-student")
  public String deleteStudentFromClass(Model model, @RequestParam("userId") String userId) {
    System.out.println(userId);
    User user = userService.findUserByUserId(Integer.parseInt(userId));
    PassioClass pClass = user.getPassioClass();
    // Tiến hành loại bỏ thông tin ở trong student và class.
    user.setPassioClass(null);
    pClass.getStudents().remove(user);
    user = userService.save(user);
    pClass = classService.save(pClass);
    if (user != null && user.getUserId() > 0 && pClass != null && pClass.getClassId() > 0)
      return ("redirect:/admin/classes/" + pClass.getClassId() + "?deleted=true");
    System.out.println("KHÔNG THỂ XOÁ ĐƯỢC SINH VIÊN!");
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
    List<CourseDTO> courseDTOs = result.getContent().stream().map(CourseMapper::convertToDTO).toList();
    model.addAttribute("courseDTOs", courseDTOs);
    model.addAttribute("course", new CourseDTO());
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
  public String addNewCourse(CourseDTO courseDTO, Model model) {
    // Hàm thêm mới một khoá học vào danh sách.
    Courses course = new Courses();
    course.setName(courseDTO.getName());
    course.setCapacity(courseDTO.getCapacity());
    course.setBegin(courseDTO.getBegin());
    course.setEnd(courseDTO.getEnd());
    course.setIntroduction(courseDTO.getIntroduction());
    course.setDescription(courseDTO.getDescription());
    course.setAttendees(0);
    course.setLessons(courseDTO.getLessons());
    course.setCategory(courseDTO.getCategory());

    boolean isOpen = new Date().before(course.getBegin());
    if (isOpen) course.setState("OPEN");
    else course.setState("CLOSED");

    MultipartFile file = courseDTO.getFile();
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
  public String updateCourse(CourseDTO courseDTO) {
    // Thực hiện cập nhật khoá học.
    int id = courseDTO.getCourseId();
    Courses course = coursesService.findByCourseId(id);
    course.setName(courseDTO.getName());
    course.setCapacity(courseDTO.getCapacity());
    course.setLessons(courseDTO.getLessons());
    course.setVote(courseDTO.vote);
    course.setIntroduction(courseDTO.getIntroduction());
    course.setDescription(courseDTO.getDescription());
    course.setBegin(courseDTO.getBegin());
    course.setEnd(courseDTO.getEnd());
    course.setLessons(courseDTO.getLessons());


    MultipartFile file = courseDTO.getFile();
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
  public String displayStudentsInCourse(Model model, @PathVariable(value = "courseId") String courseId, @RequestParam(value = "added", required = false) Boolean added, @RequestParam(value = "attended", required = false) Boolean attended, @RequestParam(value = "error", required = false) Boolean error, @RequestParam(value = "deleted", required = false) Boolean deleted, @RequestParam(value = "page", required = false) String page, @RequestParam(value = "pageSize", required = false) String pageSize, @RequestParam(value = "sortField", required = false) String sortField, @RequestParam(value = "sortDir", required = false) String sortDir) {
    Courses courses = coursesService.findByCourseId(Integer.parseInt(courseId));

    int pageNum = Integer.parseInt(page != null ? page : environment.getProperty("page"));
    int pageSizeNum = Integer.parseInt(pageSize != null ? pageSize : environment.getProperty("pageSize"));
    String field = (sortField != null ? sortField : "id");
    String dir = (sortDir != null ? sortDir : "asc");
    Page<Registrations> result = registrationsService.findAllByCourses(courses, pageNum, pageSizeNum, field, dir);
    List<User> users = result.getContent().stream().map(Registrations::getUser).toList();
    model.addAttribute("courses", courses);
    model.addAttribute("students", users);
    model.addAttribute("student", new User());
    model.addAttribute("page", pageNum);
    model.addAttribute("pageSize", pageSizeNum);
    model.addAttribute("totalPages", result.getTotalPages());
    model.addAttribute("sortField", field);
    model.addAttribute("sortDir", dir);
    if (added != null && added) {
      model.addAttribute("added", true);
      model.addAttribute("message", "Add new student successfully!");
    }
    if (error != null && error) {
      model.addAttribute("error", true);
      model.addAttribute("message", "Failed to add student to the course. The student may not exist, or the email may be incorrect!!");
    }
    if (attended != null && attended) {
      model.addAttribute("attended", true);
      model.addAttribute("message", "Student has existed in this Course!");
    }
    if (deleted != null && !deleted) {
      model.addAttribute("deleted", false);
      model.addAttribute("message", "Failed to delete student!");
    }
    return "course_students";
  }

  @PostMapping(value = "/add-student-to-course")
  public String addNewStudentInToCourse(Model model, @RequestParam(value = "email") String email, @RequestParam(value = "courseId") int courseId) {
    // Hàm thực hiện thêm một sinh viên vào 1 course.
    User user = userService.findUserByEmail(email);
    Courses courses = coursesService.findByCourseId(courseId);
    if (user == null || courses == null || user.getUserId() <= 0 || courses.getCourseId() <= 0) {
      return "redirect:/admin/courses/" + courseId + "/students?error=true";
    }
    Registrations registrations = registrationsService.findAllByCoursesAndUser(courses, user);
    if (registrations != null && registrations.getId() > 0) {
      return "redirect:/admin/courses/" + courseId + "/students?attended=true";
    }
    registrations = new Registrations();
    registrations.setUser(user);
    registrations.setCourses(courses);
    user.getRegistrations().add(registrations);
    courses.getRegistrations().add(registrations);
    userService.save(user);
    coursesService.save(courses);
    registrationsService.save(registrations);
    return "redirect:/admin/courses/" + courseId + "/students?added=true";
  }

  @PostMapping(value = "/delete-student-from-course")
  public String deleteStudentFromCourse(@RequestParam(value = "userId") int userId, @RequestParam(value = "courseId") int courseId) {
    User user = userService.findUserByUserId(userId);
    Courses courses = coursesService.findByCourseId(courseId);
    if (user == null || courses == null || user.getUserId() <= 0 || courses.getCourseId() <= 0) {
      return "redirect:/admin/courses/" + courseId + "/students?deleted=false";
    }
    Registrations registrations = registrationsService.findAllByCoursesAndUser(courses, user);
    if (registrations != null && registrations.getId() > 0) {
      user.getRegistrations().remove(registrations);
      courses.getRegistrations().remove(registrations);
      registrationsService.delete(registrations);
      userService.save(user);
      coursesService.save(courses);
      return "redirect:/admin/courses/" + courseId + "/students?deleted=true";
    }
    return "redirect:/admin/courses/" + courseId + "/students?deleted=false";
  }

  @GetMapping(value = {"messages"})
  public String displayContactMessage(@RequestParam(value = "status", required = false) String status, Model model, @RequestParam(value = "page", required = false) String page, @RequestParam(value = "pageSize", required = false) String pageSize, @RequestParam(value = "sortField", required = false) String sortField, @RequestParam(value = "sortDir", required = false) String sortDir) {
    int pageNum = Integer.parseInt((page != null) ? (page) : (environment.getProperty("page")));
    int pageSizeNum = Integer.parseInt((pageSize != null) ? (pageSize) : environment.getProperty("pageSize"));
    String field = sortField != null ? sortField : "name";
    String dir = sortDir != null ? sortDir : "asc";
    status = (status != null) ? status : "OPEN";

    Page<ContactMsg> result = contactMsgService.findAll(pageNum, pageSizeNum, field, dir);

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
    return "redirect:/admin/messages?closed=true";
  }
}
