package com.foolish.schoolmanagement.repo;

import com.foolish.schoolmanagement.model.Holiday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HolidayRepo {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public HolidayRepo(JdbcTemplate jdbcTemplate) {
    super();
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Holiday> findAll() {
    String sql = "SELECT * FROM holidays";
    var holidayRowMapper = BeanPropertyRowMapper.newInstance(Holiday.class);
    return jdbcTemplate.query(sql, holidayRowMapper);
  }

}
