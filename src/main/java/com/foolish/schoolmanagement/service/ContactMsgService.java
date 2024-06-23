package com.foolish.schoolmanagement.service;

import com.foolish.schoolmanagement.model.ContactMsg;
import com.foolish.schoolmanagement.repo.ContactMsgRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ContactMsgService {
  private final ContactMsgRepo repo;

  @Autowired
  public ContactMsgService(ContactMsgRepo repo) {
    super();
    this.repo = repo;
  }

  public List<ContactMsg> findAll() {
    return repo.findAll();
  }

  public ContactMsg findByContactID(int id) {
    return repo.findByContactId(id);
  }

  public ContactMsg save(ContactMsg message) {
    return repo.save(message);
  }

  public List<ContactMsg> findAllByStatus(String status) {
    return repo.findAllByStatus(status);
  }
}
