package com.crud.modules.customers.DAO;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.crud.modules.customers.entity.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  @Transactional
  public void save(Customer customer) {
    entityManager.createNativeQuery(
        "INSERT INTO customers (id, name, cpf, age, balance) VALUES (:id, :name, :cpf, :age, :balance)")
        .setParameter("id", customer.getId())
        .setParameter("name", customer.getName())
        .setParameter("cpf", customer.getCpf())
        .setParameter("age", customer.getAge())
        .setParameter("balance", customer.getBalance())
        .executeUpdate();
  }

  @Override
  @Transactional
  public Customer findByCpf(String cpf) {
    try {
      return (Customer) entityManager.createNativeQuery(
          "SELECT * FROM customers WHERE cpf = :cpf", Customer.class)
          .setParameter("cpf", cpf)
          .getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  @Override
  @Transactional
  public List<Customer> findAll() {
    return entityManager.createNativeQuery("SELECT * FROM customers", Customer.class)
        .getResultList();
  }

  @Override
  @Transactional
  public Customer SaveAndFetch(Customer customer) {
    save(customer);
    return findById(customer.getId());
  }

  @Override
  @Transactional
  public Customer findById(String id) {
    try {
      return (Customer) entityManager.createNativeQuery(
          "SELECT * FROM customers WHERE id = :id", Customer.class)
          .setParameter("id", id)
          .getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  @Override
  @Transactional
  public Customer findByAccount(String account) {
    try {
      return (Customer) entityManager
          .createNativeQuery("SELECT * FROM customers WHERE number_account = :account", Customer.class)
          .setParameter("account", account)
          .getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  @Override
  @Transactional
  public void updateBalance(String account, BigDecimal newBalance) {
    entityManager.createNativeQuery(
        "UPDATE customers SET balance = :newBalance WHERE number_account = :account")
        .setParameter("newBalance", newBalance)
        .setParameter("account", account)
        .executeUpdate();
  }
}
