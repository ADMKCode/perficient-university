package com.perficient.controllers;

import com.perficient.Exceptions.BusinessException;
import com.perficient.Exceptions.CustomException;
import com.perficient.dto.ErrorDTO;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(1)
public class ControllerAdvice {

  @ExceptionHandler(value = RuntimeException.class)
  public ResponseEntity<ErrorDTO> runtimeExcpetionHandler(RuntimeException e){
    ErrorDTO error = ErrorDTO.builder().code("P-500").message(e.getMessage()).build();
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = CustomException.class)
  public ResponseEntity<ErrorDTO> customExcpetionHandler(CustomException e){
    ErrorDTO error = ErrorDTO.builder().code(e.getCode()).message(e.getMessage()).build();
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = BusinessException.class)
  public ResponseEntity<ErrorDTO> businessExcpetionHandler(BusinessException e){
    ErrorDTO error = ErrorDTO.builder().code(e.getCode()).message(e.getMessage()).build();
    return new ResponseEntity<>(error, e.getStatus());
  }
}
