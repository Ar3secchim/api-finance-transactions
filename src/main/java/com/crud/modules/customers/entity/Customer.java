package com.crud.modules.customers.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "customers")
public class Customer {
  @Id
  private String id;

  @Column(name = "number_account", unique = true, nullable = false)
  private Integer numberAccount;

  @Column(nullable = false)
  private String name;

  @Column(unique = true, nullable = false)
  private String cpf;

  @Column(nullable = false)
  private Integer age;

  @Column(nullable = false)
  private BigDecimal balance = BigDecimal.ZERO;

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getCpf() {
    return cpf;
  }

  public Integer getAge() {
    return age;
  }

  public Integer getNumberAccount() {
    return numberAccount;
  }

  public BigDecimal getBalance() {
    return balance;
  }
}