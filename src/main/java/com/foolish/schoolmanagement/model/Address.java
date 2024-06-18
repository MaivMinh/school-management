package com.foolish.schoolmanagement.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
public class Address extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name="native")
  @Column(name = "address_id")
  private int addressId;

  @Column(name = "address_1")
  private String address1;
  @Column(name = "address_2")
  private String address2;

  private String city;
  private String state;
  private int zipcode;
}
