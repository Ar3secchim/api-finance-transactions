package com.crud.modules.transactions.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.modules.transactions.DAO.TransactionDAO;
import com.crud.modules.transactions.DTO.TransactionResponse;
import com.crud.modules.transactions.entity.Transaction;
import com.crud.utils.TransactionConvert;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterTransaction {
  @Autowired
  private TransactionDAO transactionDAO;

  @Transactional
  public TransactionResponse execute(Transaction transaction) {
    // validar contas

    // validar saldo não pode ser negativo ou zero nem menor que o valor da
    // transação

    // salvar transação
    transactionDAO.save(transaction);

    // atualizar saldo das contas
    return TransactionConvert.toResponse("Transação realizada com sucesso");
  }
}
