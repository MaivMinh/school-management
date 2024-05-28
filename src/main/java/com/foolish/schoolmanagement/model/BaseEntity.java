package com.foolish.schoolmanagement.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class BaseEntity {

  @CreatedDate
  @Column(name="created_at", updatable = false)
  protected LocalDateTime created_at;

  @CreatedBy
  @Column(updatable = false, name = "created_by")
  protected String created_by;

  @LastModifiedDate
  @Column(insertable = false)
  protected LocalDateTime updated_at;

  @LastModifiedBy
  @Column(insertable = false)
  protected String updated_by;

}
