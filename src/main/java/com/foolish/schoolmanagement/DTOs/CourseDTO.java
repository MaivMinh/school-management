package com.foolish.schoolmanagement.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
  public int courseId;
  public String name;
  public int fee;
  public String category;
  public int capacity;
  public int attendees;
  public MultipartFile file;
  public String img;
  public Date begin;
  public Date end;
  public String introduction;
  public String description;
  public int lessons;
  public double vote;
  public String state;


}