package com.foolish.schoolmanagement.mappers;

import com.foolish.schoolmanagement.DTOs.RegistrationDTO;
import com.foolish.schoolmanagement.model.Courses;
import com.foolish.schoolmanagement.model.Registrations;

public class RegistrationsMapper {
  public static RegistrationDTO convertToDTO(Registrations registrations) {
    return new RegistrationDTO(registrations.getCourses().getCourseId(), registrations.getCourses().getName(), null, null, registrations.getCourses().getFee(), registrations.getCourses().getBegin(), registrations.getCourses().getEnd());
  }

  public static Registrations convertToRegistrations(RegistrationDTO registrationDTO) {
    Registrations registration = new Registrations();
    Courses courses = new Courses();
    courses.setCourseId(registrationDTO.courseId);
    courses.setName(registrationDTO.name);
    courses.setFee(registrationDTO.fee);
    courses.setBegin(registrationDTO.begin);
    courses.setEnd(registrationDTO.end);
    registration.setCourses(courses);
    registration.setGrade(registrationDTO.grade);
    registration.setRating(registrationDTO.rating);
    return registration;
  }
}
