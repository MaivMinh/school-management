package com.foolish.schoolmanagement.service;

import com.foolish.schoolmanagement.model.Courses;
import com.foolish.schoolmanagement.repo.CoursesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursesService {
  private final CoursesRepo repo;

  @Autowired
  public CoursesService(CoursesRepo repo) {
    this.repo = repo;
  }

  public List<Courses> findAll() {
    return repo.findAll();
  }

  public Courses save(Courses courses) {
    return repo.save(courses);
  }

  public Courses findByCourseId(int courseId) {
    return repo.findByCourseId(courseId);
  }
}
