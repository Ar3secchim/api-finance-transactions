package com.crud.modules.customers.DTO;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CustomerResponse {
  private String id;
  private String name;
  private Integer age;
  private String cpf;
  private String numberAccount;
  private BigDecimal balance;
}
