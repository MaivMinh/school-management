package com.foolish.schoolmanagement.service;

import com.foolish.schoolmanagement.model.Courses;
import com.foolish.schoolmanagement.model.Video;
import com.foolish.schoolmanagement.repo.VideoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoService {
  private final VideoRepo videoRepo;

  public List<Video> findAllByCourses(Courses courses) {
    return videoRepo.findAllByCourses(courses);
  }
}
