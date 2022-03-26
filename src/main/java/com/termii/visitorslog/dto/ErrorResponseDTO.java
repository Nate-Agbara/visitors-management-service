package com.termii.visitorslog.dto;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO {

  private int status;
  private String detail;
}
