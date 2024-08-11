package com.foolish.schoolmanagement.service;

import com.foolish.schoolmanagement.model.Courses;
import com.foolish.schoolmanagement.model.Teach;
import com.foolish.schoolmanagement.repo.TeachRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeachService {
  private final TeachRepo teachRepo;

  @Autowired
  public TeachService(TeachRepo teachRepo) {
    this.teachRepo = teachRepo;
  }

  public List<Teach> findAllByCourses(Courses courses) {
    return teachRepo.findAllByCourses(courses);
  }
}
