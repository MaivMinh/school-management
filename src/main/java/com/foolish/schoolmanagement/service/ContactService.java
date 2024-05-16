package com.foolish.schoolmanagement.service;

import com.foolish.schoolmanagement.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ContactService {

  public boolean saveMessageDetails(Contact contact) {
    boolean isSaved = true;
    log.info(contact.toString());
    return isSaved;
  }
}
