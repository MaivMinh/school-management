package com.foolish.schoolmanagement.rest;

import com.foolish.schoolmanagement.model.ContactMsg;
import com.foolish.schoolmanagement.service.ContactMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/admin/v1")
public class AdminRestController {
  private final ContactMsgService contactMsgService;

  @Autowired
  public AdminRestController(ContactMsgService contactMsgService) {
    super();
    this.contactMsgService = contactMsgService;
  }

  @GetMapping(value = "/contacts", produces = "application/json", consumes = "application/json")
  public List<ContactMsg> displayContactMessagesByStatus(@RequestParam(value = "status") String status, @RequestParam(value = "page", required = false) String page, @RequestParam(value = "pageSize", required = false) String pageSize) {
    int pageNum = Integer.parseInt((page != null ? page: "1"));
    int pageSizeNum = Integer.parseInt(pageSize != null ? pageSize: "10");
    Page<ContactMsg> result = contactMsgService.findAllByStatus(pageNum, pageSizeNum, status);
    return result.getContent();
  }

  @GetMapping("/contacts/{contactId}")
  public ContactMsg displayContactMessage(@PathVariable(name = "contactId") String id) {
    return contactMsgService.findByContactID(Integer.parseInt(id));
  }
}
