package com.foolish.schoolmanagement.service;

import com.foolish.schoolmanagement.model.PassioClass;
import com.foolish.schoolmanagement.repo.ClassRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {
  private final ClassRepo repo;

  @Autowired
  public ClassService(ClassRepo repo) {
    super();
    this.repo = repo;
  }

  public PassioClass createNewClass(PassioClass instance) {
    return repo.save(instance);
  }

  public List<PassioClass> findAll() {
    return repo.findAll();
  }

  public PassioClass findAllByName(String name) {
    return repo.findAllByName(name);
  }

  public PassioClass findByClassId(int id) {
    return repo.findByClassId(id);
  }

  public PassioClass save(PassioClass pClass) {
    return repo.save(pClass);
  }

  public Page<PassioClass> findAll(int pageNum, int pageSizeNum, String sortField, String sortDir) {
    Pageable pageable = PageRequest.of(pageNum - 1, pageSizeNum, (sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending() ));
    return repo.findAll(pageable);
  }

  public void delete(PassioClass passioClass) {
    this.repo.delete(passioClass);
  }
}
