package com.foolish.schoolmanagement.repo;

import com.foolish.schoolmanagement.model.ContactMsg;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class ContactMsgRepo {
  private final JdbcTemplate jdbcTemplate;

  public ContactMsgRepo(JdbcTemplate jdbcTemplate) {
    super();
    this.jdbcTemplate = jdbcTemplate;
  }

  private final RowMapper<ContactMsg> contactRowMapper = (resultSet, rowNum) -> {
    ContactMsg contact = new ContactMsg();
    contact.setContact_id(resultSet.getInt("contact_id"));
    contact.setName(resultSet.getString("name"));
    contact.setEmail(resultSet.getString("email"));
    contact.setMessage(resultSet.getString("message"));
    contact.setMobileNum(resultSet.getString("mobile_num"));
    contact.setSubject(resultSet.getString("subject"));
    contact.setStatus(resultSet.getString("status"));
    contact.setCreated_at(resultSet.getTimestamp("created_at"));
    contact.setCreated_by(resultSet.getString("created_by"));
    contact.setUpdated_at(resultSet.getTimestamp("updated_at"));
    contact.setUpdated_by(resultSet.getString("updated_by"));
    return contact;
  };

  public ContactMsg getContact(int contactId) {
    String sql = "SELECT * FROM contact_msg WHERE contact_id =?";
    return jdbcTemplate.queryForObject(sql, contactRowMapper, contactId);
  }

  public ContactMsg saveMessageDetails(ContactMsg contact) {
    String sql = "INSERT INTO contact_msg (name, email, message, mobile_num, subject, status, created_at, created_by) VALUES (?,?,?,?,?,?,?,?)";
    int result = jdbcTemplate.update(sql, contact.getName(), contact.getEmail(), contact.getMessage(), contact.getMobileNum(), contact.getSubject(), "OPEN", contact.getCreated_at(), contact.getCreated_by());
    return (result > 0 ? contact: null);
  }

  public List<ContactMsg> findAll() {
    String sql = "SELECT * FROM contact_msg";
    return jdbcTemplate.query(sql, contactRowMapper);
  }

  public List<ContactMsg> findMessageWithStatus(String status) {
    String sql = "SELECT * FROM contact_msg WHERE LOWER(status)=?";
    return jdbcTemplate.query(sql, contactRowMapper, status);
  }

  public List<ContactMsg> closeContactMsg(int contact_id, String username) {
    Date current = new Date();
    String sql  = "UPDATE contact_msg SET status = 'CLOSE', updated_by=?, updated_at=? WHERE contact_id = ?";
    jdbcTemplate.update(sql, username, current, contact_id);
    return this.findAll();
  }
}
