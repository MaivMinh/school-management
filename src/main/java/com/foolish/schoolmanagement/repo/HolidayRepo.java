package com.foolish.schoolmanagement.repo;

import com.foolish.schoolmanagement.model.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolidayRepo extends JpaRepository<Holiday, Integer> {
  List<Holiday> findAllByType(Holiday.Type type);
}
