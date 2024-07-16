package com.foolish.schoolmanagement.repo;

import com.foolish.schoolmanagement.model.Courses;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesRepo extends JpaRepository<Courses, Integer> {
  Courses findByCourseId(int courseId);
  Page<Courses> findAll(Pageable pageable);
}
