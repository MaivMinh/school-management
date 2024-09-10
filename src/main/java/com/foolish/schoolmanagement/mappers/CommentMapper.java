package com.foolish.schoolmanagement.mappers;

import com.foolish.schoolmanagement.DTOs.CommentDTO;
import com.foolish.schoolmanagement.model.Comment;

public class CommentMapper {
  public static CommentDTO toCommentDTO(Comment comment) {
    return new CommentDTO(comment.getId(), comment.getUser().getUserId(), comment.getContent(), comment.getUser().getName(), comment.getUser().getImg(), comment.getCreatedAt(), comment.getType(), comment.getCourse().getCourseId(), comment.getParentComment() != null ? comment.getParentComment().getId(): null, comment.getLikes(), comment.getDisLikes(), comment.getReplies());
  }

  public static Comment toComment(CommentDTO commentDTO) {
    Comment comment = new Comment();
    comment.setId(commentDTO.getSender());
    return comment;
  }
}
