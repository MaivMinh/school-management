package com.foolish.schoolmanagement.repo;

import com.foolish.schoolmanagement.model.ContactMsg;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContactMsgRepo extends JpaRepository<ContactMsg, Integer> {
  List<ContactMsg> findAllByStatus(String status);
  ContactMsg findByContactID(int id);
}
