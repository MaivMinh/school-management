package com.foolish.schoolmanagement.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {
  public int courseId;
  public String name;
  public Double grade;
  public Double rating;
  public int fee;
  public Date begin;
  public Date end;
}