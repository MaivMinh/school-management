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
  @GenericGenerator(name = "native")
  private int courseId;

  @NotBlank
  @Size(min = 3, message = "Name must be at least 3 characters")
  private String name;

  @Column(name = "fee")
  private int fee;

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

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "courses")
  private Set<Registrations> registrations;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "courses")
  private Set<Teach> teaches;
}