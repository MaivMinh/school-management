package com.foolish.schoolmanagement.repo;

import com.foolish.schoolmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
  User getUserByEmail(String email);
}
