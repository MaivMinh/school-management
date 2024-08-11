package com.foolish.schoolmanagement.repo;

import com.foolish.schoolmanagement.model.Courses;
import com.foolish.schoolmanagement.model.Teach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeachRepo extends JpaRepository<Teach, Integer> {
  List<Teach> findAllByCourses(Courses courses);
}
