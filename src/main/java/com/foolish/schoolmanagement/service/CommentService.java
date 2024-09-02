package com.foolish.schoolmanagement.service;

import com.foolish.schoolmanagement.DTOs.CommentDTO;
import com.foolish.schoolmanagement.mappers.CommentMapper;
import com.foolish.schoolmanagement.model.Comment;
import com.foolish.schoolmanagement.model.Courses;
import com.foolish.schoolmanagement.repo.CommentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
  private final CommentRepo commentRepo;

  public CommentService(CommentRepo commentRepo) {
    this.commentRepo = commentRepo;
  }

  public List<CommentDTO> findAllByCourse(Courses courses) {
    List<Comment> comments = commentRepo.findAllByCourse(courses);
    return comments.stream().map(CommentMapper::toCommentDTO).toList();
  }
}
