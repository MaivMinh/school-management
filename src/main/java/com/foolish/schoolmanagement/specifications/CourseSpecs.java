package com.foolish.schoolmanagement.specifications;

import com.foolish.schoolmanagement.model.Courses;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class CourseSpecs {

  public static Specification<Courses> hasId(String providedId) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("courseId"), providedId);
  }

  public static Specification<Courses> containsName(String providedName) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + providedName.toLowerCase() + "%");
  }

  public static Specification<Courses> containsIntroduction(String providedName) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("introduction")), "%" + providedName.toLowerCase() + "%");
  }

  public static Specification<Courses> containsDescription(String providedName) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + providedName.toLowerCase() + "%");
  }

  public static Specification<Courses> containsCategory(String providedName) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("category")), "%" + providedName.toLowerCase() + "%");
  }

  public static Specification<Courses> startDate(Date start) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("begin"), start);
  }

  public static Specification<Courses> betweenFee(int start, int end) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("fee"), start, end);
  }
}
