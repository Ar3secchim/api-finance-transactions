package com.crud.modules.customers.DAO;

import java.math.BigDecimal;
import java.util.List;

import com.crud.modules.customers.entity.Customer;

public interface CustomerDAO {
  Customer findByCpf(String cpf);

  void updateBalance(String account, BigDecimal newBalance);

  Customer findById(String id);

  Customer findByAccount(String account);

  void save(Customer customer);

  List<Customer> findAll();

  Customer SaveAndFetch(Customer customer);
}
