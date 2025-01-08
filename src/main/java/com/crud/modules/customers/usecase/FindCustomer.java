package com.crud.modules.customers.usecase;

import org.springframework.beans.factory.annotation.Autowired;
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
  @Autowired
  private CustomerDAO customerDAO;

  public CustomerResponse findByCpf(String cpf) throws Exception {
    Customer customer = customerDAO.findByCpf(cpf);

    if (customer == null) {
      throw new Exception("Customer not found with cpf: " + cpf);
    }
    return CustomerConvert.toResponse(customer);
  }

  public CustomerResponse findById(String id) throws BadRequestClient {
    Customer customer = customerDAO.findById(id);

    if (customer == null) {
      throw new BadRequestClient("Customer not found with id: " + id);
    }

    return CustomerConvert.toResponse(customer);
  }
}
