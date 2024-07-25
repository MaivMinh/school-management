package com.foolish.schoolmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name="contact_msg")
public class ContactMsg extends BaseEntity {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
  @GenericGenerator(name = "native")
  private int contactId;

  @NotBlank(message = "* Name is required")
  @Pattern(regexp = "^$|[a-zA-Z ]{3,25}$", message = "* Name must be only ASCII characters and length between 3 and 25")
  private String name;

  @NotBlank(message = "* Mobile number is required")
  @Pattern(regexp = "^$|[0-9]{10}$", message = "* Mobile number must be 10 digits")
  @Column(name="mobile_num")
  private String mobileNum;

  @NotBlank(message = "* Email is required")
  @Email(message = "* Invalid email")
  private String email;

  @NotBlank(message = "* Subject is required")
  @Size(min = 3, message = "* Subject must be at least 3 characters")
  private String subject;

  @NotBlank(message = "* Message is required")
  @Size(min = 3, message = "* Message must be at least 3 characters")
  private String message;

  private String status = "OPEN";
}
