package com.foolish.schoolmanagement.controller;

import com.foolish.schoolmanagement.service.CoursesService;
import com.foolish.schoolmanagement.service.TeachService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CoursesControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  CoursesService coursesService;
  @MockBean
  TeachService teachService;

  @InjectMocks
  CoursesController coursesController;

  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void displayCourses() {
  }

  @Test
  void displayDetailedCourse() {
  }
}