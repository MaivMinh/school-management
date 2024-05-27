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

  public ContactMsg saveMessageDetails(ContactMsg contact) {
    return repo.saveMessageDetails(contact);
  }

  public ContactMsg getContactById(int id) {
    return repo.getContact(id);
  }

  public List<ContactMsg> findAll() {
    return repo.findAll();
  }

  public List<ContactMsg> findMessageWithStatus(String status) {
    return repo.findMessageWithStatus(status);
  }

  public List<ContactMsg> closeContactMsg(int contact_id) {
    return repo.closeContactMsg(contact_id);
  }
}
