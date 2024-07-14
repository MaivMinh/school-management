package com.foolish.schoolmanagement.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Courses extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  private int courseId;

  @NotBlank
  @Size(min = 3, message = "Name must be at least 3 characters")
  private String name;

  private String fees;

  @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private Set<User> users = new HashSet<>();
}