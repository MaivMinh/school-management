package com.foolish.schoolmanagement.exceptions;

public class CoursesNotFoundException extends RuntimeException{
  public CoursesNotFoundException(int id) {
    super("Could not found Course with ID " + id);
  }
}
