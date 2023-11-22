package com.test.obs.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class CustomErrorException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private String responseMessage;
  public CustomErrorException(String msg) {

    super(msg);
  }
}
