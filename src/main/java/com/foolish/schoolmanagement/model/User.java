package com.foolish.schoolmanagement.model;

import com.foolish.schoolmanagement.annotations.FieldsValueMatch;
import com.foolish.schoolmanagement.annotations.PasswordValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

@EqualsAndHashCode(callSuper = true)
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
@Table(name = "Users")
public class User extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name="native")
  private Integer userId;

  @NotBlank
  @Pattern(regexp = "^$|[a-zA-Z ]{3,25}$", message = "* Name must be only ASCII characters and length between 3 and 25")
  private String name;

  @NotBlank
  @Pattern(regexp = "^$|[0-9]{10}$", message = "* Mobile number must be 10 digits")
  @Column(name = "mobile_number")
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


  @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST, targetEntity = Roles.class)
  @JoinColumn(name = "role_id", referencedColumnName = "roleId",nullable = false)
  private Roles roles;

  @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL, targetEntity = Address.class)
  @JoinColumn(name = "address_id", referencedColumnName = "addressId",nullable = true)
  private Address address;

  @Override
  public String toString() {
    return "User{" +
            "userId=" + userId +
            ", name='" + name + '\'' +
            ", mobileNum='" + mobileNum + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            '}';
  }
}
