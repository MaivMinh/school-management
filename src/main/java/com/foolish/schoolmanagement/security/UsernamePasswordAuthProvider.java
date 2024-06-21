package com.foolish.schoolmanagement.security;

import com.foolish.schoolmanagement.model.Roles;
import com.foolish.schoolmanagement.model.User;
import com.foolish.schoolmanagement.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class UsernamePasswordAuthProvider implements AuthenticationProvider {

  private final UserRepo userRepo;

  @Autowired
  public UsernamePasswordAuthProvider(UserRepo _userRepo) {
    super();
    this.userRepo = _userRepo;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String email = authentication.getName();
    String password = authentication.getCredentials().toString();
    User user = userRepo.getUserByEmail(email);
    if (user != null && user.getUserId() > 0 && password.equals(user.getPassword())) {
      return new UsernamePasswordAuthenticationToken(user.getName(), null, getGrantedAuthorities(user.getRoles()));
    } else {
      throw new BadCredentialsException("Invalid credentials!");
    }
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }


  public Collection<GrantedAuthority> getGrantedAuthorities(Roles roles) {
    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + roles.getRoleName()));
    return grantedAuthorities;
  }
}
