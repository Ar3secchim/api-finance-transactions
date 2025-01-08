package com.crud.modules.transactions.DTO;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class TransactionRequest {
  private String customerOriginId;
  private String customerDestinyId;
  private BigDecimal value;
}