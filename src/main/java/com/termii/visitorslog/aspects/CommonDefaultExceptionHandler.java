package com.termii.visitorslog.aspects;

import com.termii.visitorslog.dto.ErrorResponseDTO;
import com.termii.visitorslog.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
@Order(Ordered.LOWEST_PRECEDENCE)
public class CommonDefaultExceptionHandler {

  /**
   * This method handle all the Exception which belongs to Exception.class
   *
   * @param exception
   * @return ResponseEntity<Response>
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDTO> handleException(Exception exception) {
    log.error("HandleException : {}", exception.getMessage(), exception);
    return Response.setErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }

}
