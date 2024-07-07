package com.foolish.schoolmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Class extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  private int classId;

  @NotBlank(message = "Name must not be blank")
  @Size(min = 3, message = "Name must be at least 3 characters")
  private String name;

  @OneToMany(mappedBy = "aClass", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, targetEntity = User.class)
  private Set<User> students;

}
