package com.foolish.schoolmanagement.service;

import com.foolish.schoolmanagement.model.Holiday;
import com.foolish.schoolmanagement.repo.HolidayRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayService {
  private final HolidayRepo repo;

  @Autowired
  public HolidayService(HolidayRepo repo) {
    super();
    this.repo = repo;
  }

  public List<Holiday> findAll() {
    return repo.findAll();
  }

  public List<Holiday> findAllByType(Holiday.Type type) {
    return repo.findAllByType(type);
  }
}
