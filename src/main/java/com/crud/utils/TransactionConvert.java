package com.crud.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import com.crud.modules.transactions.DTO.TransactionRequest;
import com.crud.modules.transactions.entity.Transaction;

public class TransactionConvert {
  public static Transaction toEntity(TransactionRequest transactionRequest) {
    Transaction transaction = new Transaction();

    transaction.setId(UUID.randomUUID().toString());
    transaction.setOriginAccount(transactionRequest.getOriginAccount());
    transaction.setDestinyAccount(transactionRequest.getDestinyAccount());
    transaction.setValue(transactionRequest.getValue());
    transaction.setAtCreated(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

    return transaction;
  }
}