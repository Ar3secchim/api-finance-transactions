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
        "INSERT INTO transactions (id, origin_account, destiny_account, value, at_created) VALUES (:id, :originAccount, :destinyAccount, :value, :atCreated)")
        .setParameter("id", transaction.getId())
        .setParameter("originAccount", transaction.getOriginAccount())
        .setParameter("destinyAccount", transaction.getDestinyAccount())
        .setParameter("value", transaction.getValue())
        .setParameter("atCreated", transaction.getAtCreated())
        .executeUpdate();
  }
}
