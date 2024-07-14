package com.foolish.schoolmanagement.service;

import com.foolish.schoolmanagement.model.Roles;
import com.foolish.schoolmanagement.model.User;
import com.foolish.schoolmanagement.repo.RolesRepo;
import com.foolish.schoolmanagement.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
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

  public User getUserByEmail(String email) {
    return userRepo.getUserByEmail(email);
  }

  public User save(User user) {
    return userRepo.save(user);
  }

  public User findUserByUserId(Integer userId) {
    return userRepo.findUserByUserId(userId);
  }
}
