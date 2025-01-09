package com.crud.infra.exception;

public class DuplicateCpfException extends RuntimeException {
  public DuplicateCpfException(String message) {
    super(message);
  }
}