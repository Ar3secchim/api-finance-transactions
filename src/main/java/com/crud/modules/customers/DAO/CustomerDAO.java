package com.crud.modules.customers.DAO;

import java.util.List;

import com.crud.modules.customers.entity.Customer;

public interface CustomerDAO {
  Customer findByCpf(String cpf);

  // TODO create update void updateCustomerBalance(String cpf, Double newBalance);

  void save(Customer customer);

  List<Customer> findAll();

  Customer SaveAndFetch(Customer customer);
}
