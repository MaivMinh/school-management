package com.foolish.schoolmanagement.rest;

import com.foolish.schoolmanagement.model.ContactMsg;
import com.foolish.schoolmanagement.service.ContactMsgService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/contacts")
public class ContactMsgRestController {
  private final ContactMsgService contactMsgService;

  @Autowired
  public ContactMsgRestController(ContactMsgService contactMsgService) {
    super();
    this.contactMsgService = contactMsgService;
  }

  @PostMapping(value = "/save")
  public ResponseEntity<ContactMsg> saveContactMessage(@RequestHeader(value = "invocationFrom") String invocationFrom, @Valid @RequestBody ContactMsg contact) {
    log.info(String.format("Invocation from: %s", invocationFrom)); // Ghi lại nhật kí kiểu INFO.
    contact = contactMsgService.save(contact);
    if (contact != null && contact.getContactId() > 0) {
      //  Nếu chúng ta chỉ gửi Response object về thay vì ResponseEntity, thì khi đó Client sẽ chỉ nhận được dữ liệu JSON ở phần Body chứa thông tin phía dưới.
      // Client có được JSON ở phần Body là do chúng ta có Jackson.
    return ResponseEntity
            .status(HttpStatus.CREATED)
            .header("Content-Type", "application/json")
            .body(contact);
    }
    return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .header("Content-Type", "application/json")
            .body(contact);
  }

}
