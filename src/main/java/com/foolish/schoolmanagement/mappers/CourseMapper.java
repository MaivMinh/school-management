package com.foolish.schoolmanagement.mappers;

import com.foolish.schoolmanagement.DTOs.CoursesDTO;
import com.foolish.schoolmanagement.DTOs.UserDTO;
import com.foolish.schoolmanagement.model.Courses;
import com.foolish.schoolmanagement.model.User;

import java.util.List;

public class CourseMapper {
  public static CoursesDTO convertToDTO(Courses courses) {
    return new CoursesDTO(courses.getCourseId(), courses.getName(), courses.getFee(), courses.getCategory(), courses.getCapacity(), courses.getAttendees(), courses.getImg(), courses.getBegin(), courses.getEnd(), courses.getIntroduction(), courses.getDescription(), courses.getLessons(), courses.getVote(), courses.getState());
  }

  public static Courses convertToUser(CoursesDTO coursesDTO) {
    Courses courses = new Courses();
    courses.setCourseId(coursesDTO.courseId());
    courses.setName(coursesDTO.name());
    courses.setFee(coursesDTO.fee());
    courses.setCategory(coursesDTO.category());
    courses.setCapacity(coursesDTO.capacity());
    courses.setAttendees(coursesDTO.attendees());
    courses.setImg(coursesDTO.img());
    courses.setBegin(coursesDTO.begin());
    courses.setEnd(coursesDTO.end());
    courses.setIntroduction(coursesDTO.introduction());
    courses.setDescription(coursesDTO.description());
    courses.setLessons(coursesDTO.lessons());
    courses.setVote(coursesDTO.vote());
    courses.setState(coursesDTO.state());
    return courses;
  }
}
