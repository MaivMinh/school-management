package com.foolish.schoolmanagement.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

@EqualsAndHashCode(callSuper = true)
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
