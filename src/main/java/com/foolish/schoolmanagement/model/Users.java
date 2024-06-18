package com.foolish.schoolmanagement.model;

import com.foolish.schoolmanagement.annotations.FieldsValueMatch;
import com.foolish.schoolmanagement.annotations.PasswordValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@FieldsValueMatch.List(
        {
                @FieldsValueMatch(
                        field = "password",
                        fieldMatch = "confirmPassword",
                        message = "Password do not match!"
                ),
                @FieldsValueMatch(
                        field = "email",
                        fieldMatch = "confirmEmail",
                        message = "Email address do not match!"
                )
        }
)
public class Users extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name="native")
  private Integer userId;

  @NotBlank
  @Pattern(regexp = "^$|[a-zA-Z ]{3,25}$", message = "* Name must be only characters and length between 3 and 25")
  private String name;

  @NotBlank
  @Pattern(regexp = "^$|[0-9]{10}$", message = "* Mobile number must be 10 digits")
  private String mobileNum;

  @NotBlank(message = "* Email must not be blank")
  @Email(message = "* Please provide a valid email address")
  private String email;

  @Transient
  private String confirmEmail;

  @NotBlank (message = "* Password must no be blank")
  @PasswordValidator
  @Size(min = 6, message = "Password must be at least 6 characters")
  private String password;

  @Transient
  private String confirmPassword;

  @Override
  public String toString() {
    return "Users{" +
            "userId=" + userId +
            ", name='" + name + '\'' +
            ", mobileNum='" + mobileNum + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            '}';
  }
}
