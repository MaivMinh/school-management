package com.foolish.schoolmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

  @CreatedDate
  @Column(name="created_at", updatable = false)
  private Date created_at;

  @CreatedBy
  @Column(updatable = false, name = "created_by")
  private String created_by;

  @LastModifiedDate
  @Column(insertable = false)
  private Date updated_at;

  @LastModifiedBy
  @Column(insertable = false)
  private String updated_by;

}
