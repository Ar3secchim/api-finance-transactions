package com.crud.modules.customers.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.infra.exception.BadRequestClient;
import com.crud.modules.customers.DAO.CustomerDAO;
import com.crud.modules.customers.DTO.CustomerResponse;
import com.crud.modules.customers.entity.Customer;
import com.crud.utils.CustomerConvert;
import com.crud.utils.ValidateCpf;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterCustomer {
  @Autowired
  private CustomerDAO customerDAO;

  @Transactional
  public CustomerResponse execute(Customer customer) throws Exception {
    // TODO - Implementar validação de CPF
    customer.setCpf(customer.getCpf().replaceAll("[^0-9]", ""));

    if (!ValidateCpf.execute(customer.getCpf())) {
      throw new BadRequestClient("Invalid CPF");
    }

    if (customerDAO.findByCpf(customer.getCpf()) != null) {
      throw new BadRequestClient("CPF já está cadastrado");
    }

    if (customer.getName() == null || customer.getName().isEmpty()) {
      throw new BadRequestClient("Name is required");
    }

    if (customer.getAge() == null || customer.getAge() < 18) {
      throw new BadRequestClient("Age is required and must be greater than 18");
    }

    Customer customerSave = customerDAO.SaveAndFetch(customer);
    return CustomerConvert.toResponse(customerSave);
  }
}
