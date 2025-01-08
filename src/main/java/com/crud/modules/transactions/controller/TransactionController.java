package com.crud.modules.transactions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.modules.transactions.DTO.TransactionRequest;
import com.crud.modules.transactions.DTO.TransactionResponse;
import com.crud.modules.transactions.usecase.RegisterTransaction;
import com.crud.utils.TransactionConvert;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {
  @Autowired
  RegisterTransaction registerTransaction;

  @Operation(summary = "Create a transaction", description = "Returns a transaction")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Successfully retrieved"),
      @ApiResponse(responseCode = "400", description = "Not possible make transaction")
  })
  @PostMapping
  public ResponseEntity<TransactionResponse> createTransaction(
      @Valid @RequestBody TransactionRequest transactionRequest) throws Exception {
    TransactionResponse transactionResponse = registerTransaction
        .execute(TransactionConvert.toEntity(transactionRequest));

    return ResponseEntity.ok()
        .body(transactionResponse);
  }
}
