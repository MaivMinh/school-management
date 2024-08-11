package com.foolish.schoolmanagement.mappers;

import com.foolish.schoolmanagement.DTOs.RegistrationsDTO;
import com.foolish.schoolmanagement.model.Courses;
import com.foolish.schoolmanagement.model.Registrations;

public class RegistrationsMapper {
  public static RegistrationsDTO convertToDTO(Registrations registrations) {
    return new RegistrationsDTO(registrations.getCourses().getCourseId(), registrations.getCourses().getName(), registrations.getGrade() != null ? registrations.getGrade() : 0, registrations.getRating() != null ? registrations.getRating() : 0, registrations.getCourses().getFee(), registrations.getCourses().getBegin(), registrations.getCourses().getEnd());
  }

  public static Registrations convertToRegistrations(RegistrationsDTO registrationsDTO) {
    Registrations registrations = new Registrations();
    Courses courses = new Courses();
    courses.setCourseId(registrationsDTO.courseId());
    courses.setName(registrationsDTO.name());
    courses.setFee(registrationsDTO.fee());
    courses.setBegin(registrationsDTO.begin());
    courses.setEnd(registrationsDTO.end());
    registrations.setCourses(courses);
    registrations.setGrade(registrationsDTO.grade());
    registrations.setRating(registrationsDTO.rating());
    return registrations;
  }
}
