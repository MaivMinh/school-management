package com.foolish.schoolmanagement.controller;

import com.foolish.schoolmanagement.model.PassioClass;
import com.foolish.schoolmanagement.model.User;
import com.foolish.schoolmanagement.service.ClassService;
import com.foolish.schoolmanagement.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@Data
@RequestMapping("/admin")
public class AdminController {
  private final ClassService classService;
  private final UserService userService;

  @Autowired
  public AdminController(ClassService classService, UserService userService) {
    super();
    this.classService = classService;
    this.userService = userService;
  }

  @GetMapping("display-courses")
  public String displayCourses() {
    return "courses";
  }

  @GetMapping("display-classes")
  public String displayClasses(Model model, @RequestParam(value = "success", required = false) String success, @RequestParam(value = "existed", required = false) String existed) {
    model.addAttribute("classes", classService.findAll());
    if (success != null && success.equalsIgnoreCase("true")) {
      model.addAttribute("success", true);
    } else if (success != null && success.equalsIgnoreCase("false")) {
      model.addAttribute("success", false);
    } else if (existed != null && existed.equalsIgnoreCase("true")) {
      model.addAttribute("existed", true);
    }
    return "classes";
  }

  @GetMapping("display-classes/{classId}")
  public String displayStudentInClassWithClassId(Model model, @PathVariable("classId") String classId, @RequestParam(value = "success", required = false) String success, @RequestParam(value = "error", required = false) String error, @RequestParam(value = "deleted", required = false) String deleted) {
    PassioClass passioClass = classService.findByClassId(Integer.parseInt(classId));
    model.addAttribute("passioClass", passioClass);
    model.addAttribute("students", passioClass.getStudents());
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
      return "redirect:/admin/display-classes?existed=true";
    }
    PassioClass addedInstance = classService.createNewClass(instance);
    List<PassioClass> classes = classService.findAll();
    model.addAttribute("classes", classes);
    if (addedInstance != null && addedInstance.getClassId() > 0) {
      // success.
      return "redirect:/admin/display-classes?success=true";
    }
    // error
    return "redirect:/admin/display-classes?success=false";
  }

  @PostMapping("add-student")
  public String addStudentIntoClass(Model model, User user, @RequestParam("id") String id) {
    user = userService.getUserByEmail(user.getEmail());
    if (user != null && user.getUserId() > 0 && user.getAPassioClass() == null) {
      PassioClass pClass = classService.findByClassId(Integer.parseInt(id));
      if (pClass != null && pClass.getClassId() > 0) {
        pClass.getStudents().add(user); // Thêm student vào class.
        user.setAPassioClass(pClass); // Thêm class vào student.
        userService.save(user);
        classService.save(pClass);
      } else return ("redirect:/admin/display-classes/" + id + "?error=true");
    } else return ("redirect:/admin/display-classes/" + id + "?error=true");
    return ("redirect:/admin/display-classes/" + id + "?success=true");
  }

  @PostMapping("delete-student")
  public String deleteStudentFromClass(Model model, @RequestParam("userId") String userId) {
    User user = userService.findUserByUserId(Integer.parseInt(userId));
    PassioClass pClass = user.getAPassioClass();
    // Tiến hành loại bỏ thông tin ở trong student and class.
    user.setAPassioClass(null);
    user = userService.save(user);
    if (user != null && user.getUserId() > 0)
      return ("redirect:/admin/display-classes/" + pClass.getClassId() + "?deleted=true");
    return ("redirect:/admin/display-classes/" + pClass.getClassId() + "?deleted=false");
  }
}
