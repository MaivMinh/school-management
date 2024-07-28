package com.foolish.schoolmanagement.rest;

import com.foolish.schoolmanagement.model.ContactMsg;
import com.foolish.schoolmanagement.model.Response;
import com.foolish.schoolmanagement.service.ContactMsgService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/admin")
@CrossOrigin(origins = "*")
public class AdminRestController {
  private static final Logger log = LoggerFactory.getLogger(AdminRestController.class);
  private final ContactMsgService contactMsgService;
  private final Environment environment;

  @Autowired
  public AdminRestController(ContactMsgService contactMsgService, Environment environment) {
    super();
    this.contactMsgService = contactMsgService;
    this.environment = environment;
  }

  @GetMapping(value = "/contacts", produces = "application/json", consumes = "application/json")
  public ResponseEntity<List<ContactMsg>> displayContactMessagesByStatus(@RequestParam(value = "status") String status, @RequestParam(value = "page", required = false) String page, @RequestParam(value = "pageSize", required = false) String pageSize) {
    int pageNum = Integer.parseInt((page != null ? page : environment.getProperty("page")));
    int pageSizeNum = Integer.parseInt(pageSize != null ? pageSize : environment.getProperty("pageSize"));
    Page<ContactMsg> result = contactMsgService.findAllByStatus(pageNum, pageSizeNum, status);
    List<ContactMsg> list = result.getContent();
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(list);
  }

  @GetMapping(value = "/contacts/{contactId}", produces = "application/json", consumes = "application/json")
  public ResponseEntity<ContactMsg> displayContactMessage(@PathVariable(name = "contactId") String id) {
    ContactMsg contact = contactMsgService.findByContactID(Integer.parseInt(id));
    if (contact != null && contact.getContactId() > 0) {
      return ResponseEntity
              .status(HttpStatus.OK)
              .body(contact);
    }
    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(contact);
  }

  @PostMapping("/save")
  public ResponseEntity<ContactMsg> saveContactMessage(@RequestBody @Valid ContactMsg contact) {
    contact = contactMsgService.save(contact);
    if (contact != null && contact.getContactId() > 0) {
      return ResponseEntity
              .status(HttpStatus.CREATED)
              .body(contact);
    }
    return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(contact);
  }

  @DeleteMapping(value = "/delete", consumes = "application/json", produces = "application/json")
  public ResponseEntity<Response> deleteContactMessage(RequestEntity<ContactMsg> request) {
    HttpHeaders headers = request.getHeaders();
    headers.forEach((key, value) -> {
      log.info(String.format("Header '%s' = '%s'", key, value));
    });
    ContactMsg contact = request.getBody();
    contactMsgService.deleteContactMessage(contact);
    Response response = new Response();
    response.setStatus("200");
    response.setMessage("Message is deleted successfully");
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(response);
  }

  @PatchMapping(value = "/close")
  public ResponseEntity<Response> closeContactMessage(@RequestBody @Valid ContactMsg contact) {
    ContactMsg contactMsg = contactMsgService.findByContactID(contact.getContactId());
    if (contactMsg != null && contactMsg.getContactId() > 0) {
      contactMsg.setStatus("Close");
      contactMsgService.save(contactMsg);
      return ResponseEntity
              .status(HttpStatus.OK)
              .body(new Response("200", "Message closed successfully"));
    }
    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new Response("400", "Invalid Contact ID Received"));
  }
}
