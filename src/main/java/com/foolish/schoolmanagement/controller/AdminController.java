package com.foolish.schoolmanagement.controller;

import com.foolish.schoolmanagement.model.PassioClass;
import com.foolish.schoolmanagement.service.ClassService;
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

  @Autowired
  public AdminController(ClassService classService) {
    super();
    this.classService = classService;
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
  public String displayStudentInClassWithClassId(Model model, @PathVariable("classId") String classId) {
    PassioClass passioClass = classService.findByClassId(Integer.parseInt(classId));
    model.addAttribute("passioClass", passioClass);
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

}
