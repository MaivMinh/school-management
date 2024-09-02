package com.foolish.schoolmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class LikedComment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  // Đối tuợng LikedComment này thuộc Comment nào.
  @ManyToOne
  @JoinColumn(name = "comment_id")
  private Comment comment;

  // Đối tuợng LikedComment này của User nào tạo.
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
}