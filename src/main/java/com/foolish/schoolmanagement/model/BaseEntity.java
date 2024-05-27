package com.foolish.schoolmanagement.model;

import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {
  public BaseEntity() {
    this.created_at = new Date();
  }
  private Date created_at;
  private String created_by;
  private Date updated_at;
  private String updated_by;

}
