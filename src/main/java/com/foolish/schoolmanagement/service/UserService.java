package com.foolish.schoolmanagement.service;

import com.foolish.schoolmanagement.model.Roles;
import com.foolish.schoolmanagement.model.User;
import com.foolish.schoolmanagement.repo.RolesRepo;
import com.foolish.schoolmanagement.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepo userRepo;
  private final RolesRepo roleRepo;

  @Autowired
  public UserService(UserRepo userRepo, RolesRepo roleRepo) {
    super();
    this.userRepo = userRepo;
    this.roleRepo = roleRepo;
  }

  public boolean createNewUser(User user) {
    Roles roles = roleRepo.getRolesByRoleName(Roles.STUDENT_ROLE);
    user.setRoles(roles);
    user = userRepo.save(user);
    if (user != null && user.getUserId() > 0)
      return true;
    return false;
  }
}
