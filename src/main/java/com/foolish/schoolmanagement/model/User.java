package com.foolish.schoolmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
  @Id
  private Integer userId;
  private String name;
}
