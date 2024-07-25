package com.foolish.schoolmanagement.exceptions;

import com.foolish.schoolmanagement.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
@Order(1)
public class GlobalExceptionsRestController extends ResponseEntityExceptionHandler {

  // Override lại phương thức với mục đích xử lý MethodArgumentNotValidException. Phải sử dụng @Valid này ở @RequestBody
  // Xem ví dụ: AdminRestController.closeContactMessage(...)
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new Response(status.toString(), ex.getBindingResult().toString()));
  }

  @ExceptionHandler({RuntimeException.class})
  public ResponseEntity<Response> handleRuntimeException(RuntimeException exception) {
    log.error(String.format("RuntimeException : %s", exception.getMessage()));
    log.error(String.format("RuntimeException caused by : %s", exception.getCause()));
    return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new Response("500", exception.getMessage()));
  }
}
