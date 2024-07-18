package com.foolish.schoolmanagement.repo;

import com.foolish.schoolmanagement.model.PassioClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClassRepo extends JpaRepository<PassioClass, Integer> {
  PassioClass findAllByName(String name);
  PassioClass findByClassId(int id);
  Page<PassioClass> findAll(Pageable pageable);
}
