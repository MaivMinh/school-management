package com.foolish.schoolmanagement.controller;

import com.foolish.schoolmanagement.model.PassioClass;
import com.foolish.schoolmanagement.service.ClassService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@Data
@RequestMapping("/admin")
public class AdminController {
  private final ClassService service;

  @Autowired
  public AdminController(ClassService service) {
    super();
    this.service = service;
  }

  @GetMapping("display-courses")
  public String displayCourses() {
    return "courses";
  }

  @GetMapping("display-classes")
  public String displayClasses(Model model, @RequestParam(value = "success", required = false) String success) {
    model.addAttribute("classes", service.findAll());
    if (success != null && success.equalsIgnoreCase("true")) {
      model.addAttribute("success", true);
    } else if (success != null && success.equalsIgnoreCase("false")) {
      model.addAttribute("success", false);
    }
    return "classes";
  }

  @PostMapping("create-new-class")
  public String createNewClass(Model model, PassioClass instance) {
    PassioClass addedInstance = service.createNewClass(instance);
    List<PassioClass> classes = service.findAll();
    model.addAttribute("classes", classes);
    if (addedInstance != null && addedInstance.getClassId() > 0) {
      // success.
      return "redirect:/display-classes?success=true";
    }
    // error
    return "redirect:/display-classes?success=false";
  }


}
