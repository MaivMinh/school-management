package com.foolish.schoolmanagement.repo;

import com.foolish.schoolmanagement.model.Courses;
import com.foolish.schoolmanagement.model.Registrations;
import com.foolish.schoolmanagement.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RegistrationsRepo extends JpaRepository<Registrations, Integer> {
  Page<Registrations> findAllByUser(User user, Pageable pageable);
  Page<Registrations> findAllByCourses(Courses courses, Pageable pageable);
}
