package com.foolish.schoolmanagement.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

  @Column(name = "fee")
  private String fees;

  private String category;
  @Size(min = 20, message = "Capacity of Course at least 30 students")
  private int capacity;
  private int attendees;
  private String img;
  private java.sql.Date begin;
  private java.sql.Date end;
  private String introduction;
  private String description;
  private int lessons;
  private double vote;
  private String state;


  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, optional = false)
  @JoinColumn(name = "lecturer", referencedColumnName = "userId", nullable = false)
  @NotNull
  private User lecturer;

  @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private Set<User> users = new HashSet<>();

  @Override
  public String toString() {
    return "Courses{" +
            "courseId=" + courseId +
            ", name='" + name + '\'' +
            ", fees='" + fees + '\'' +
            '}';
  }
}