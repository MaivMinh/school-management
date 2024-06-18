package com.foolish.schoolmanagement.repo;

import com.foolish.schoolmanagement.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Users, Integer> {
  
}
