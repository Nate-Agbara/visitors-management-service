package com.termii.visitorslog.aspects;

import com.termii.visitorslog.dto.ErrorResponseDTO;
import com.termii.visitorslog.exception.ApiException;
import com.termii.visitorslog.exception.ApiRequestException;
import com.termii.visitorslog.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CommonControllerExceptionHandler {
  /**
   * This method handles HttpMessageNotReadableException.class, when request body
   * not found
   *
   * @param exception
   * @return ResponseEntity<Response>
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException(
    HttpMessageNotReadableException exception) {
    log.error("Exception Handler for HttpMessageNotReadableException : {}", exception.getMessage());
    return Response.setErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }

  /**
   * This method handles IllegalArgumentException
   *
   * @param exception
   * @return ResponseEntity<Response>
   */
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponseDTO> handleIllegalArgumentException(IllegalArgumentException exception) {
    log.error("Exception Handler for IllegalArgumentException : {}", exception.getMessage());
    return Response.setErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }

  /**
   * This method handle MethodArgumentTypeMismatchException
   *
   * @param exception
   * @return ResponseEntity<Response>
   */
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ErrorResponseDTO> handleMethodArgumentTypeMismatchException(
    MethodArgumentTypeMismatchException exception) {
    log.error("Exception Handler for HandleMethodArgumentNotValidException : {}", exception.getMessage());
    return Response.setErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }

  /**
   * This method handle MethodArgumentNotValidException, when request parameter is
   * invalid
   *
   * @param error
   * @return
   */
    @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException error) {
    log.error("Exception Handler for handleMethodArgumentNotValidException : {}", error.getMessage());
    return Response.setErrorResponse(
      error.getBindingResult().getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {ApiRequestException.class})
  public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
    log.error("Exception Handler for ApiRequestException : {}", e.getMessage());
    HttpStatus badRequest = HttpStatus.BAD_REQUEST;
    ApiException apiException = new ApiException(
            e.getMessage(),
            badRequest,
            ZonedDateTime.now(ZoneId.of("Z"))
    );
    return new ResponseEntity<>(apiException, badRequest);
  }

}
