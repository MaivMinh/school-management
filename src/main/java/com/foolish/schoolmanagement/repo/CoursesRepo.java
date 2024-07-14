package com.foolish.schoolmanagement.repo;

import com.foolish.schoolmanagement.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CoursesRepo extends JpaRepository<Courses, Integer> {
  Courses findByCourseId(int courseId);
}
