package com.foolish.schoolmanagement.repo;

import com.foolish.schoolmanagement.model.Courses;
import com.foolish.schoolmanagement.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepo extends JpaRepository<Video, Integer> {
  public List<Video> findAllByCourses(Courses courses);
}
