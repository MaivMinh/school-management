package com.foolish.schoolmanagement.repo;

import com.foolish.schoolmanagement.model.PassioClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepo extends JpaRepository<PassioClass, Integer> {
}
