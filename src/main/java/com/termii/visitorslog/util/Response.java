package com.termii.visitorslog.util;

import com.termii.visitorslog.dto.ErrorResponseDTO;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;

@NoArgsConstructor
public final class Response {

  /**
   * Sets ResponseDTO data and HTTP Status.
   *
   * @param data       : ResponseDTO of back-end processing.
   * @param httpStatus : Success and failure code.
   * @return : This will Spring response entity.
   */
  public static ResponseEntity<?> setResponse(Object data, HttpStatus httpStatus) {
    return new ResponseEntity<>(data, httpStatus);
  }

  /**
   * Sets ResponseDTO data and HTTP Status.
   *
   * @param httpStatus : Success and failure code.
   * @return : This will Spring response entity.
   */
  public static ResponseEntity<Object> setDeleteResponse(HttpStatus httpStatus) {
    return new ResponseEntity<>(httpStatus);
  }

  /**
   * Sets ResponseDTO HTTP Status.
   *
   * @param httpStatus Success and failure code.
   * @return This will Spring response entity.
   */
  public static ResponseEntity<Object> setResponseHeadersWithData(String key, String value, Object object, HttpStatus httpStatus) {
    MultiValueMap<String, String> headers = new HttpHeaders();
    headers.add(key, value);
    return new ResponseEntity<>(object, headers, httpStatus);
  }

  /**
   * Sets ResponseDTO HTTP Status.
   *
   * @param httpStatus Success and failure code.
   * @return This will Spring response entity.
   */
  public static ResponseEntity<Object> setResponseWithData(Object object, HttpStatus httpStatus) {
    return new ResponseEntity<>(object, httpStatus);
  }

  /**
   * Sets location and HTTP Status.
   *
   * @param httpStatus Success and failure code.
   * @return This will Spring response entity.
   */
  public static ResponseEntity<Object> setLocationHeaders(String key, String value, HttpStatus httpStatus) {
    MultiValueMap<String, String> headers = new HttpHeaders();
    headers.add(key, value);
    return new ResponseEntity<>(headers, httpStatus);
  }

  /**
   * Creates response entity with message and httpStatus.
   *
   * @param message    ResponseDTO message
   * @param httpStatus HTTP status code for success and failure
   * @return This will Spring response entity.
   */
  public static ResponseEntity<ErrorResponseDTO> setErrorResponse(String message, HttpStatus httpStatus) {

    ErrorResponseDTO response = new ErrorResponseDTO();
    response.setStatus(httpStatus.value());
    response.setDetail(message);

    MultiValueMap<String, String> headers = new HttpHeaders();
    headers.add("Content-Type", "application/problem+json");
    return new ResponseEntity<>(response,headers, httpStatus);
  }


  /**
   * Utility method to create current date.
   *
   * @return String converted date.
   */
  public static String getCurrentDate() {
    LocalDateTime currentDateTime = LocalDateTime.now();
    return currentDateTime.toString();
  }

}
