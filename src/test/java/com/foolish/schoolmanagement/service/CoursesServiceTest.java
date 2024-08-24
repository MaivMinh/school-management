package com.foolish.schoolmanagement.service;

import com.foolish.schoolmanagement.exceptions.CoursesNotFoundException;
import com.foolish.schoolmanagement.model.Courses;
import com.foolish.schoolmanagement.repo.ClassRepo;
import com.foolish.schoolmanagement.repo.CoursesRepo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@Slf4j
class CoursesServiceTest {

  @Mock
  CoursesRepo coursesRepo;

  @InjectMocks
  CoursesService coursesService;

  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void save() {
  }

  @Test
  void testFindByCourseIdSuccess() {
    // Given.
    // 1. Arrange Input.
    Courses courses = new Courses();
    courses.setCourseId(1);
    courses.setName("System Design");
    courses.setLessons(30);
    courses.setCapacity(50);
    courses.setFee(1000);

    // 2. Arrange Target.
    given(coursesRepo.findByCourseId(1)).willReturn(courses);

    // When. Act on the target behavior. When steps should cover the method to be tested.
    Courses returnedCourses = coursesService.findByCourseId(1);

    // Then.
    assertThat(returnedCourses.getCourseId() == courses.getCourseId());
    assertThat(returnedCourses.getName().equals(courses.getName()));
    assertThat(returnedCourses.getLessons() == courses.getLessons());
    assertThat(returnedCourses.getCapacity() == courses.getCapacity());
    assertThat(returnedCourses.getFee() == courses.getFee());

    // Verify coursesRepo.findByCourseId() is called exactly once with "1".
    //verify(this.artifactRepository, times(1)).findById(1);
    verify(this.coursesRepo, times(1)).findByCourseId(1);
  }

  @Test
  void testFindByCourserIdNotFound() {
    // Given.
    given(coursesRepo.findByCourseId(Mockito.any(Integer.class))).willReturn(null);

    // When.
    Throwable throwable = catchThrowable(() -> {
      Courses returnedCourses = coursesService.findByCourseId(1);
    });

    // Then.
    assertThat(throwable)
            .isInstanceOf(CoursesNotFoundException.class)
            .hasMessage("Could not found Course with ID 1");

    verify(this.coursesRepo, times(1)).findByCourseId(1);
  }

  @Test
  void testDisplayCoursesSuccess() {
  }

  @Test
  void testFindCoursesByCriteriaSuccess() {
  }
}