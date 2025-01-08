package com.crud.modules.transactions.DTO;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class TransactionRequest {
  private String originAccount;
  private String destinyAccount;
  private BigDecimal value;
}