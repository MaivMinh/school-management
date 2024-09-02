package com.foolish.schoolmanagement.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
  private Integer sender;           // Id của người gửi.
  private String content;           // Nội dung của comment.
  private LocalDateTime createdAt;  // Thời gian viết comment. Có ngày/tháng/năm và giờ cụ thể.
  private MessageType type;           // Là một comment mới hay là một reply.
  private Integer courseId;         // Khoá học mà chứa comment.
  private Integer parentCommentId;  // Nếu là một reply thì lưu thêm thông tin parent comment.
  private Integer likes;            // Số lượng lượt like.
  private Integer disLikes;         // Số lượng lượt like.
  private Integer replies;          // Số lượng reply.

  public enum MessageType {
    COMMENT,
    REPLY
  }
}
