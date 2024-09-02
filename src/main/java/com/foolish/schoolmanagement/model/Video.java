package com.foolish.schoolmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Video {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String src;
  private Integer part;
  private String tag;
  @ManyToOne
  @JoinColumn(name = "course_id")
  private Courses courses;
}
