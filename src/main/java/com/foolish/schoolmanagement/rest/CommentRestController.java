package com.foolish.schoolmanagement.rest;

import com.foolish.schoolmanagement.DTOs.CommentDTO;
import com.foolish.schoolmanagement.mappers.CommentMapper;
import com.foolish.schoolmanagement.model.Comment;
import com.foolish.schoolmanagement.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/comments")
public class CommentRestController {
  private final CommentService commentService;

  @GetMapping(value = "/replies")
  public ResponseEntity<List<CommentDTO>> getReplyComments(@RequestParam Integer id) {
    // API thực hiện trả về Reply Comment với Parent Comment tương ứng.
    Comment parent = commentService.findAllById(id);
    List<CommentDTO> comments = commentService.findAllByParentComment(parent);
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(comments);
  }
}
