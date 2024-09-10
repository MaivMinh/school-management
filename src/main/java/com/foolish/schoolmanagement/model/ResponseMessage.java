package com.foolish.schoolmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {
  private Integer id;
  private Integer sender;           // Id của người gửi.
  private String content;           // Nội dung của comment.
  private String name;
  private String img;
  private LocalDateTime createdAt;  // Thời gian viết comment. Có ngày/tháng/năm và giờ cụ thể.
  private String type;           // Là một comment mới hay là một reply.
  private Integer courseId;         // Khoá học mà chứa comment.
  private Integer parentCommentId;  // Nếu là một reply thì lưu thêm thông tin parent comment.
  private Integer likes;            // Số lượng lượt like.
  private Integer disLikes;         // Số lượng lượt like.
  private Integer replies;          // Số lượng reply.

  public ResponseMessage(Comment comment) {
    this.id = comment.getId();
    this.sender = comment.getUser().getUserId();
    this.content = comment.getContent();
    this.name = comment.getUser().getName();
    this.createdAt = comment.getCreatedAt();
    this.type = comment.getType();
    this.courseId = comment.getCourse().getCourseId();
    this.parentCommentId = comment.getParentComment() != null ? comment.getParentComment().getId(): null;
    this.likes = comment.getLikes();
    this.disLikes = comment.getDisLikes();
    this.replies = comment.getReplies();
    this.img = comment.getUser().getImg();
  }
}
