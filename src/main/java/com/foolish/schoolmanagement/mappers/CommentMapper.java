package com.foolish.schoolmanagement.mappers;

import com.foolish.schoolmanagement.DTOs.CommentDTO;
import com.foolish.schoolmanagement.model.Comment;

public class CommentMapper {
  public static CommentDTO toCommentDTO(Comment comment) {
    return new CommentDTO(comment.getId(), comment.getContent(), comment.getCreatedAt(), comment.getUser().getUserId(), comment.getUser().getName(), comment.getUser().getImg(), comment.getCourse().getCourseId(), comment.getParentComment() != null ? comment.getParentComment().getId(): null);
  }

  public static Comment toComment(CommentDTO commentDTO) {
    Comment comment = new Comment();
    comment.setId(commentDTO.getId());
    return comment;
  }
}
