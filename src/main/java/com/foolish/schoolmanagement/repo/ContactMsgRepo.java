package com.foolish.schoolmanagement.repo;

import com.foolish.schoolmanagement.model.ContactMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ContactMsgRepo extends JpaRepository<ContactMsg, Integer> {
  List<ContactMsg> findAllByStatus(String status);
}
