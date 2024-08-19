package com.foolish.schoolmanagement.mappers;

import com.foolish.schoolmanagement.DTOs.CourseDTO;
import com.foolish.schoolmanagement.model.Courses;

import java.util.List;

public class CourseMapper {
  public static CourseDTO convertToDTO(Courses courses) {
    return new CourseDTO(courses.getCourseId(), courses.getName(), courses.getFee(), courses.getCategory(), courses.getCapacity(), courses.getAttendees(), null, courses.getImg(), courses.getBegin(), courses.getEnd(), courses.getIntroduction(), courses.getDescription(), courses.getLessons(), courses.getVote(), courses.getState());
  }

  public static Courses convertToCourses(CourseDTO courseDTO) {
    Courses courses = new Courses();
    courses.setCourseId(courseDTO.courseId);
    courses.setName(courseDTO.name);
    courses.setFee(courseDTO.fee);
    courses.setCategory(courseDTO.category);
    courses.setCapacity(courseDTO.capacity);
    courses.setAttendees(courseDTO.attendees);
    courses.setImg(courseDTO.img);
    courses.setBegin(courseDTO.begin);
    courses.setEnd(courseDTO.end);
    courses.setIntroduction(courseDTO.introduction);
    courses.setDescription(courseDTO.description);
    courses.setLessons(courseDTO.lessons);
    courses.setVote(courseDTO.vote);
    courses.setState(courseDTO.state);
    return courses;
  }
}
