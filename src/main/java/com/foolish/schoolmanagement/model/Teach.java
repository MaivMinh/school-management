package com.foolish.schoolmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
public class Teach {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name="native")
  private int id;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "userId")
  private User lecture;

  @ManyToOne
  @JoinColumn(name = "course_id", referencedColumnName = "courseId")
  private Courses courses;
}
