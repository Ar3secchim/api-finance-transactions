package com.crud.infra;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.crud.infra.exception.BadRequestClient;
import com.crud.infra.exception.DuplicateCpfException;
import com.crud.infra.exception.InvalidRequestException;

@ControllerAdvice
public class GlobalExceptionHandler {

  // Manipulador para BadRequestClient
  @ExceptionHandler(BadRequestClient.class)
  public ResponseEntity<Map<String, String>> handleBadRequestClient(BadRequestClient ex) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put("error", ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  // Manipulador para CPF Duplicado
  @ExceptionHandler(DuplicateCpfException.class)
  public ResponseEntity<Map<String, String>> handleDuplicateCpfException(DuplicateCpfException ex) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put("error", ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  // Manipulador para Requisição Inválida
  @ExceptionHandler(InvalidRequestException.class)
  public ResponseEntity<Map<String, String>> handleInvalidRequestException(InvalidRequestException ex) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put("error", ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  // Manipulador genérico
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put("error", "Erro interno do servidor.");
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}