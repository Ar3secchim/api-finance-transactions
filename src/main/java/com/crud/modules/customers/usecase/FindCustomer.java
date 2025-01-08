package com.crud.modules.customers.usecase;

import org.springframework.stereotype.Service;

import com.crud.infra.exception.BadRequestClient;
import com.crud.modules.customers.DAO.CustomerDAO;
import com.crud.modules.customers.DTO.CustomerResponse;
import com.crud.modules.customers.entity.Customer;
import com.crud.utils.CustomerConvert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindCustomer {
  private CustomerDAO customerDAO;

  public CustomerResponse findById(String cpf) throws BadRequestClient {
    Customer customer = customerDAO.findByCpf(cpf);

    if (customer == null) {
      throw new BadRequestClient("Customer not found with cpf: " + cpf);
    }
    return CustomerConvert.toResponse(customer);
  }
}
