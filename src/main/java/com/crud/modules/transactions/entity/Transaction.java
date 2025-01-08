package com.crud.modules.transactions.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
@Table(name = "transactions")
public class Transaction {
  @Id
  private String id;

  @Column(name = "origin_account", nullable = false)
  private String originAccount;

  @Column(name = "destiny_account", nullable = false)
  private String destinyAccount;

  @Column(nullable = false)
  private BigDecimal value;

  @Column(name = "at_created", nullable = false)
  private String AtCreated;

  public String getId() {
    return id;
  }

  public String getOriginAccount() {
    return originAccount;
  }

  public String getDestinyAccount() {
    return destinyAccount;
  }

  public BigDecimal getValue() {
    return value;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

  public String getAtCreated() {
    return AtCreated;
  }
}
