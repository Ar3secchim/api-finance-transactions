package com.crud.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.crud.modules.customers.DTO.CustomerRequest;
import com.crud.modules.customers.DTO.CustomerResponse;
import com.crud.modules.customers.entity.Customer;

public class CustomerConvert {
  public static Customer toEntity(CustomerRequest customerRequest) {
    Customer customer = new Customer();

    customer.setId(UUID.randomUUID().toString());
    customer.setCpf(customerRequest.getCpf());
    customer.setName(customerRequest.getName());
    customer.setAge(customerRequest.getAge());
    return customer;
  }

  public static CustomerResponse toResponse(Customer customer) {
    CustomerResponse customerResponse = new CustomerResponse();
    String cpf = customer.getCpf();
    int numberAccount = customer.getNumberAccount();

    customerResponse.setId(customer.getId());
    customerResponse.setCpf(
        String.format("%s.%s.%s-%s",
            cpf.substring(0, 3),
            cpf.substring(3, 6),
            cpf.substring(6, 9),
            cpf.substring(9, 11)));
    customerResponse.setName(customer.getName());
    customerResponse.setAge(customer.getAge());
    customerResponse.setNumberAccount(String.format("%06d", numberAccount));
    customerResponse.setBalance(customer.getBalance());

    return customerResponse;
  }

  public static List<CustomerResponse> toListResponse(List<Customer> listCustomer) {
    if (listCustomer == null)
      return new ArrayList<>();

    return listCustomer.stream()
        .map(CustomerConvert::toResponse)
        .collect(Collectors.toList());
  }
}
