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

  public Comment findAllById(Integer id) {
    return  commentRepo.findAllById(id);
  }

  public Comment save(Comment comment) {
    return commentRepo.save(comment);
  }

  public List<CommentDTO> findAllByParentComment(Comment parent) {
    return commentRepo.findAllByParentComment(parent).stream().map(CommentMapper::toCommentDTO).toList();
  }

}
