package com.crud.modules.transactions.DAO;

import org.springframework.stereotype.Repository;

import com.crud.modules.transactions.entity.Transaction;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class TransactionDAOImpl implements TransactionDAO {
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  @Transactional
  public void save(Transaction transaction) {
    entityManager.createNativeQuery(
        "INSERT INTO transactions (id, origin_account, destiny_account, value) VALUES (:id, :origin_account, :destiny_account, :value) ",
        Transaction.class)
        .setParameter("id", transaction.getId())
        .setParameter("value", transaction.getValue())
        .setParameter("origin_account", transaction.getOriginAccount())
        .setParameter("destiny_account", transaction.getOriginAccount())
        .executeUpdate();
  }
}
