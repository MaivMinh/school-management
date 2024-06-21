package com.foolish.schoolmanagement.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
public class Roles extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native")
  private int roleId;
  private String roleName;

  public static final String STUDENT_ROLE = "STUDENT";
  public static final String ADMIN_ROLE = "ADMIN";
}
