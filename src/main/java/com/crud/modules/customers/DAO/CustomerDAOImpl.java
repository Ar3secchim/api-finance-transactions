package com.crud.modules.customers.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crud.modules.customers.entity.Customer;

import jakarta.persistence.EntityManager;
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
    return (Customer) entityManager.createNativeQuery(
        "SELECT * FROM customers WHERE cpf = :cpf", Customer.class)
        .setParameter("cpf", cpf)
        .getSingleResult();
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
    return (Customer) entityManager.createNativeQuery(
        "SELECT * FROM customers WHERE id = :id", Customer.class)
        .setParameter("id", customer.getId())
        .getSingleResult();
  }
}
