package com.perficient.Exceptions;

import lombok.Data;

//Excepcion personalizada
@Data
public class CustomException extends RuntimeException {
  private String code;
  public CustomException(String code, String message) {
    super(message);
    this.code = code;
  }
}
