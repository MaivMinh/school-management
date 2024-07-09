package com.foolish.schoolmanagement.service;

import com.foolish.schoolmanagement.model.PassioClass;
import com.foolish.schoolmanagement.repo.ClassRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
}
