package com.foolish.schoolmanagement.DTOs;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
  private Integer id;
  private String content;
  private LocalDateTime createdAt;
  private Integer userId;
  private String userName;
  private String img;
  private Integer courseId;
  private Integer parentId;
}
