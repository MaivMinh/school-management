package com.foolish.schoolmanagement.service;

import com.foolish.schoolmanagement.model.Courses;
import com.foolish.schoolmanagement.model.User;
import com.foolish.schoolmanagement.repo.CoursesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursesService {
  private final CoursesRepo repo;

  @Autowired
  public CoursesService(CoursesRepo repo) {
    this.repo = repo;
  }

  public Courses save(Courses courses) {
    return repo.save(courses);
  }

  public Courses findByCourseId(int courseId) {
    return repo.findByCourseId(courseId);
  }

  public Page<Courses> displayCourses(int pageNum, int pageSizeNum, String field, String dir) {
    Pageable pageable = PageRequest.of(pageNum - 1, pageSizeNum, (dir.equals("asc") ? Sort.by(field).ascending() : Sort.by(field).descending() ));
    return repo.findAll(pageable);
  }

}
