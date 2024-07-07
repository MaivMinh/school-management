package com.foolish.schoolmanagement.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

  /*
  * insertable=false có nghĩa rằng khi chúng ta sử dụng câu lệnh SQL INSERT thì không được phép có chứa 2 trường là updated_at và updated_by, vì đơn giản nếu có chứa 2 trường này thì dữ liệu updated_at và updated_by sẽ có giá trị rồi trong khi chưa có thao tác update nào xảy ra trước.
  * updatable=false có nghĩa rằng khi chúng ta sử dụng câu lệnh SQL UPDATE thì không được phép có chứa 2 trường là created_at và created_by, vì đơn giản nếu có chứa 2 trường này thì dữ liệu created_at và created_by sẽ được cập nhật và sẽ không biết ai là người tạo ra dòng dữ liệu này.
  * */
  @CreatedDate
  @Column(updatable = false)
  protected LocalDateTime createdAt;

  @CreatedBy
  @Column(updatable = false)
  protected String createdBy;

  @LastModifiedDate
  @Column(insertable = false)
  protected LocalDateTime updatedAt;

  @LastModifiedBy
  @Column(insertable = false)
  protected String updatedBy;
}
