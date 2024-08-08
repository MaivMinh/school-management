package com.foolish.schoolmanagement.service;

import com.foolish.schoolmanagement.model.Courses;
import com.foolish.schoolmanagement.model.PassioClass;
import com.foolish.schoolmanagement.model.Roles;
import com.foolish.schoolmanagement.model.User;
import com.foolish.schoolmanagement.repo.RolesRepo;
import com.foolish.schoolmanagement.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepo userRepo;
  private final RolesRepo roleRepo;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(UserRepo userRepo, RolesRepo roleRepo, PasswordEncoder _passwordEncoder) {
    super();
    this.userRepo = userRepo;
    this.roleRepo = roleRepo;
    this.passwordEncoder = _passwordEncoder;
  }

  public boolean createNewUser(User user) {
    Roles roles = roleRepo.getRolesByRoleName(Roles.STUDENT_ROLE);
    user.setRoles(roles);
    // Hashing password before storing into database.
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user = userRepo.save(user);
    if (user != null && user.getUserId() > 0)
      return true;
    return false;
  }

  public User findUserByEmail(String email) {
    return userRepo.getUserByEmail(email);
  }

  public User save(User user) {
    return userRepo.save(user);
  }

  public User findUserByUserId(Integer userId) {
    return userRepo.findUserByUserId(userId);
  }

  public Page<User> findUsersByPassioClass(PassioClass passioClass, int pageNum, int pageSize, String dir, String field) {
    Pageable pageable = PageRequest.of(pageNum - 1, pageSize, (dir.equalsIgnoreCase("asc") ? Sort.by(field).ascending() : Sort.by(field).descending()));
    return userRepo.findAllByPassioClass(passioClass, pageable);
  }
}
