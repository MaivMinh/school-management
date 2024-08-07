package com.foolish.schoolmanagement.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(annotations = Controller.class) // Sử dụng kiến thức của String AOP để tạo ra một Advice cho toàn bộ ứng dụng.
// Vì các Exception cũng là một cross cutting concern xuyên suốt quá trình chạy ứng dụng.
// Nên việc sử dụng ControllerAdvice để handle Exception tỏ ra hiệu quả.
public class GlobalExceptionsController {

  @ExceptionHandler({RuntimeException.class, Exception.class})  // Dùng chon toàn bộ exception xảy ra bên trong ứng dụng.
  public String handleRuntimeException(RuntimeException exception, Model model) {
    model.addAttribute("message", exception.getMessage());
    exception.printStackTrace();
    return "error";
  }
}
