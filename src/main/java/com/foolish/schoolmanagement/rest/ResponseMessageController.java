package com.foolish.schoolmanagement.rest;

import com.foolish.schoolmanagement.model.Message;
import com.foolish.schoolmanagement.model.ResponseMessage;
import jakarta.validation.Valid;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseMessageController {
  @MessageMapping("/comment")
  @SendTo("/topic/comments")
  public ResponseMessage createComment(Message message) throws Exception {
    return new ResponseMessage(message);
  }

}
