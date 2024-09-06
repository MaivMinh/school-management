package com.foolish.schoolmanagement.rest;

import com.foolish.schoolmanagement.DTOs.CommentDTO;
import com.foolish.schoolmanagement.mappers.CommentMapper;
import com.foolish.schoolmanagement.model.*;
import com.foolish.schoolmanagement.service.CommentService;
import com.foolish.schoolmanagement.service.CoursesService;
import com.foolish.schoolmanagement.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ResponseMessageController {
  private final CommentService commentService;
  private final UserService userService;
  private final CoursesService coursesService;

  @MessageMapping("/comment")
  @SendTo("/topic/comments")
  public ResponseMessage createComment(Message message) throws Exception {
    Comment comment = new Comment();
    User user = userService.findUserByUserId(message.getSender());
    comment.setContent(message.getContent());
    comment.setUser(user);
    Comment parent = commentService.findAllById(message.getParentCommentId());
    comment.setParentComment(parent);
    comment.setCreatedAt(message.getCreatedAt());
    Courses courses = coursesService.findByCourseId(message.getCourseId());
    comment.setCourse(courses);
    commentService.save(comment);
    return new ResponseMessage(message);
  }

}
