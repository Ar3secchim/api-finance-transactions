package com.crud.modules.customers.usecase;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.infra.exception.BadRequestClient;
import com.crud.infra.exception.InvalidRequestException;
import com.crud.modules.customers.DAO.CustomerDAO;
import com.crud.modules.customers.DTO.BalanceResquest;
import com.crud.modules.customers.entity.Customer;
import com.crud.utils.ResponseMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateBalanceCustomer {
  @Autowired
  private CustomerDAO customerDAO;

  public ResponseMessage execute(BalanceResquest balanceResquest) throws BadRequestClient, InvalidRequestException {
    Customer customer = customerDAO.findByAccount(balanceResquest.getAccount());

    if (customer == null) {
      throw new BadRequestClient("Conta não encontrada");
    }

    if (balanceResquest.getBalance().compareTo(BigDecimal.ZERO) <= 0) {
      throw new InvalidRequestException("Valor do deposito deve ser maior que zero");
    }

    customerDAO.updateBalance(balanceResquest.getAccount(), balanceResquest.getBalance());
    return new ResponseMessage("Saldo atualizado com sucesso");
  }
}
