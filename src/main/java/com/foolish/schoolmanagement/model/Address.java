package com.foolish.schoolmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Address extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name="native")
  private int addressId;

  @NotBlank(message="Address1 must not be blank")
  @Size(min=5, message="Address1 must be at least 5 characters long")
  @Column(name = "address_1")
  private String address1;

  @Column(name = "address_2")
  private String address2;

  @NotBlank(message="City must not be blank")
  @Size(min=5, message="City must be at least 5 characters long")
  @Column(name = "city")
  private String city;

  @NotBlank(message="State must not be blank")
  @Size(min=5, message="State must be at least 5 characters long")
  private String state;

  @NotBlank(message="Zip Code must not be blank")
  @Pattern(regexp="(^$|[0-9]{5})",message = "Zip Code must be 5 digits")
  private String zipcode;
}