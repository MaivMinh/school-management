package com.foolish.schoolmanagement.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class NewCourse {
  private String name;
  private String fee;
  private String category;
  private int capacity;
  private MultipartFile file;
  private java.sql.Date begin;
  private java.sql.Date end;
  private String lecturer;
  private String description;
  private String lessons;
}