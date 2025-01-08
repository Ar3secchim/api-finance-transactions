package com.crud.infra.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BadRequestClient extends RuntimeException {
  public BadRequestClient(String message) {
    super(message);
  }
}
