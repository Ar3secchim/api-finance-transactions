package com.crud.utils;

import java.time.LocalDateTime;
import java.util.UUID;

import com.crud.modules.transactions.DTO.TransactionRequest;
import com.crud.modules.transactions.DTO.TransactionResponse;
import com.crud.modules.transactions.entity.Transaction;

public class TransactionConvert {
  public static Transaction toEntity(TransactionRequest transactionRequest) {
    Transaction transaction = new Transaction();

    transaction.setId(UUID.randomUUID().toString());
    transaction.setOriginAccount(transactionRequest.getCustomerOriginId());
    transaction.setDestinyAccount(transactionRequest.getCustomerDestinyId());
    transaction.setValue(transactionRequest.getValue());
    transaction.setAtCreated(LocalDateTime.now().toString());

    return transaction;
  }

  public static TransactionResponse toResponse(String message) {
    TransactionResponse transactionResponse = new TransactionResponse();

    transactionResponse.setMessage(message);

    return transactionResponse;
  }
}
