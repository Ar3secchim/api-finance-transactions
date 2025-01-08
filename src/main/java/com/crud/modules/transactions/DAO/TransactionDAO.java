package com.crud.modules.transactions.DAO;

import com.crud.modules.transactions.entity.Transaction;

public interface TransactionDAO {
  void save(Transaction transaction);

  // TODO create List<Transaction> findAll();
  // TODO create Transaction findById(String id);
}
