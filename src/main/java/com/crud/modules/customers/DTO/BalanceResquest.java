package com.crud.modules.customers.DTO;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BalanceResquest {
  private String account;
  private BigDecimal balance;
}
