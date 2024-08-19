package com.foolish.schoolmanagement.repo;

import com.foolish.schoolmanagement.model.ContactMsg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContactMsgRepo extends JpaRepository<ContactMsg, Integer> {
  List<ContactMsg> findAllByStatus(String status);
  ContactMsg findByContactId(int id);

  Page<ContactMsg> findAllByStatus(String status, Pageable pageable);
  Page<ContactMsg> findAll(Pageable pageable);
}
