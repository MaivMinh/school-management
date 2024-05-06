package com.foolish.schoolmanagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentRestController {

  @RequestMapping(value = "/students", produces = "application/json")
  public String getStudents() {
    return "[{\"id\":1,\"name\":\"John\",\"age\":20},{\"id\":2,\"name\":\"Jane\",\"age\":21}]";
  }
}
