package com.foolish.schoolmanagement.service;

import com.foolish.schoolmanagement.exceptions.CoursesNotFoundException;
import com.foolish.schoolmanagement.model.Courses;
import com.foolish.schoolmanagement.model.User;
import com.foolish.schoolmanagement.repo.CoursesRepo;
import com.foolish.schoolmanagement.specifications.CourseSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Service
public class CoursesService {
  private final CoursesRepo repo;

  @Autowired
  public CoursesService(CoursesRepo repo) {
    this.repo = repo;
  }

  public Courses save(Courses courses) {
    return repo.save(courses);
  }

  public Courses findByCourseId(int courseId) {
    Courses courses = repo.findByCourseId(courseId);
    if (courses != null)  return courses;
    else throw new CoursesNotFoundException(courseId);
  }

  public Page<Courses> displayCourses(int pageNum, int pageSizeNum, String field, String dir) {
    Pageable pageable = PageRequest.of(pageNum - 1, pageSizeNum, (dir.equals("asc") ? Sort.by(field).ascending() : Sort.by(field).descending() ));
    return repo.findAll(pageable);
  }

  public List<Courses> findCoursesByCriteria(Map<String, String> searchCriteria) {
    Specification<Courses> specs = Specification.where(null);
    if (searchCriteria.get("courseId") != null && !searchCriteria.get("courseId").isEmpty())  {
      specs = specs.and(CourseSpecs.hasId(searchCriteria.get("courseId")));
    }
    if (searchCriteria.get("name") != null && !searchCriteria.get("name").isEmpty())  {
      specs = specs.and(CourseSpecs.containsName(searchCriteria.get("name")));
    }
    if (searchCriteria.get("category") != null && !searchCriteria.get("category").isEmpty())  {
      specs = specs.and(CourseSpecs.containsCategory(searchCriteria.get("category")));
    }
    if (searchCriteria.get("description") != null && !searchCriteria.get("description").isEmpty())  {
      specs = specs.and(CourseSpecs.containsDescription(searchCriteria.get("description")));
    }
    if (searchCriteria.get("startFee") != null && !searchCriteria.get("startFee").isEmpty() && searchCriteria.get("endFee") != null && !searchCriteria.get("endFee").isEmpty())  {
      specs = specs.and(CourseSpecs.betweenFee(Integer.parseInt(searchCriteria.get("startFee")), Integer.parseInt(searchCriteria.get("endFee"))));
    }
    if (searchCriteria.get("start") != null && !searchCriteria.get("start").isEmpty()) {
      specs = specs.and(CourseSpecs.startDate(new Date(2024, 5, 10)));
    }
    return this.repo.findAll(specs);
  }
}
