package com.foolish.schoolmanagement.repo;

import com.foolish.schoolmanagement.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Integer> {
}
