package com.foolish.schoolmanagement.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "holidays")
public class Holiday extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int holiday_id;

  private String day;
  private String reason;
  @Enumerated(EnumType.STRING)
  private Type type;

  public enum Type {
    FESTIVAL, FEDERAL
  }

}
