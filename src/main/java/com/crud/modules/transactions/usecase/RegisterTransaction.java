package com.crud.modules.transactions.usecase;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.infra.exception.BadRequestClient;
import com.crud.infra.exception.InvalidRequestException;
import com.crud.modules.customers.DAO.CustomerDAO;
import com.crud.modules.customers.entity.Customer;
import com.crud.modules.transactions.DAO.TransactionDAO;
import com.crud.modules.transactions.entity.Transaction;
import com.crud.utils.ResponseMessage;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterTransaction {
  @Autowired
  private TransactionDAO transactionDAO;

  @Autowired
  private CustomerDAO customerDAO;

  @Transactional
  public ResponseMessage execute(Transaction transaction) throws BadRequestClient, InvalidRequestException {
    // Formatar as contas
    String originAccount = formatAccount(transaction.getOriginAccount());
    String destinationAccount = formatAccount(transaction.getDestinyAccount());

    // Buscar contas no banco de dados
    Customer originAccountExist = customerDAO.findByAccount(originAccount);
    Customer destinationAccountExist = customerDAO.findByAccount(destinationAccount);

    // Validar se as contas existem
    if (originAccountExist == null || destinationAccountExist == null) {
      throw new BadRequestClient("Conta de origem ou destino não existe");
    }

    // Validar se as contas são diferentes
    if (originAccount.equals(destinationAccount)) {
      throw new InvalidRequestException("Conta de origem e destino não podem ser iguais");
    }

    // Validar valor da transferência
    if (transaction.getValue().compareTo(BigDecimal.ZERO) <= 0) {
      throw new InvalidRequestException("Valor da transferência deve ser maior que zero");
    }

    // Validar saldo
    if (originAccountExist.getBalance().compareTo(transaction.getValue()) < 0) {
      throw new InvalidRequestException("Saldo insuficiente para a transação.");
    }

    // Salvar transação
    transaction.setOriginAccount(originAccount);
    transaction.setDestinyAccount(destinationAccount);
    transactionDAO.save(transaction);

    // Atualizar saldo das contas (exemplo simplificado)
    originAccountExist.setBalance(originAccountExist.getBalance().subtract(transaction.getValue()));
    destinationAccountExist.setBalance(destinationAccountExist.getBalance().add(transaction.getValue()));

    return new ResponseMessage("Transferência realizada com sucesso");
  }

  private String formatAccount(String account) {
    return account.trim();
  }
}
