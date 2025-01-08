package com.crud.modules.customers.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.modules.customers.DAO.CustomerDAO;
import com.crud.modules.customers.DTO.CustomerResponse;
import com.crud.modules.customers.entity.Customer;
import com.crud.utils.CustomerConvert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListCustomer {
  @Autowired
  private CustomerDAO customerDAO;

  public List<CustomerResponse> execute() {
    List<Customer> customers = customerDAO.findAll();
    return CustomerConvert.toListResponse(customers);
  }
}
