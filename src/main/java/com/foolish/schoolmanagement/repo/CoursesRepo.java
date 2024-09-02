package com.foolish.schoolmanagement.repo;

import com.foolish.schoolmanagement.model.Courses;
import com.foolish.schoolmanagement.model.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CoursesRepo extends JpaRepository<Courses, Integer>, JpaSpecificationExecutor<Courses> {
  Courses findByCourseId(int courseId);
  Page<Courses> findAll(Pageable pageable);
  Page<Courses> findAllByCategory(String category, Pageable pageable);
}
