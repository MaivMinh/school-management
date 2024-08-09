package com.foolish.schoolmanagement.repo;

import com.foolish.schoolmanagement.model.Courses;
import com.foolish.schoolmanagement.model.PassioClass;
import com.foolish.schoolmanagement.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Integer> {
  User getUserByEmail(String email);
  User findUserByUserId(Integer id);
  Page<User> findAllByPassioClass(PassioClass passioClass, Pageable pageable);
}
