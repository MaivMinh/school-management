package com.foolish.schoolmanagement.service;

import com.foolish.schoolmanagement.model.Courses;
import com.foolish.schoolmanagement.model.Registrations;
import com.foolish.schoolmanagement.model.User;
import com.foolish.schoolmanagement.repo.RegistrationsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RegistrationsService {
  private final RegistrationsRepo registrationsRepo;

  @Autowired
  public RegistrationsService(RegistrationsRepo registrationsRepo) {
    this.registrationsRepo = registrationsRepo;
  }

  public Page<Registrations> findAllByUser(User user, int page, int pageSize, String field, String dir) {
    Pageable pageable = PageRequest.of(page - 1, pageSize, (dir.equalsIgnoreCase("asc") ? Sort.by(field).ascending(): Sort.by(field).descending()));
    return registrationsRepo.findAllByUser(user, pageable);
  }

  public Page<Registrations> findAllByCourses(Courses courses, int page, int pageSize, String field, String dir) {
    Pageable pageable = PageRequest.of(page - 1, pageSize, (dir.equalsIgnoreCase("asc") ? Sort.by(field).ascending() : Sort.by(field).descending()));
    return registrationsRepo.findAllByCourses(courses, pageable);
  }
}
