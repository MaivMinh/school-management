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
  private Integer sender;           // Id của người gửi.
  private String content;           // Nội dung của comment.
  private String name;
  private String img;
  private LocalDateTime createdAt;  // Thời gian viết comment. Có ngày/tháng/năm và giờ cụ thể.
  private Enum type;           // Là một comment mới hay là một reply.
  private Integer courseId;         // Khoá học mà chứa comment.
  private Integer parentCommentId;  // Nếu là một reply thì lưu thêm thông tin parent comment.
  private Integer likes;            // Số lượng lượt like.
  private Integer disLikes;         // Số lượng lượt like.
  private Integer replies;          // Số lượng reply.

  public ResponseMessage(Message message) {
    this.sender = message.getSender();
    this.content = message.getContent();
    this.name = message.getName();
    this.createdAt = message.getCreatedAt();
    this.type = message.getType();
    this.courseId = message.getCourseId();
    this.parentCommentId = message.getParentCommentId();
    this.likes = message.getLikes();
    this.disLikes = message.getDisLikes();
    this.replies = message.getReplies();
    this.img = message.getImg();
  }
}
