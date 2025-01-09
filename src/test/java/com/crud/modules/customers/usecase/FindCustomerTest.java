package com.crud.modules.customers.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.crud.infra.exception.BadRequestClient;
import com.crud.modules.customers.DAO.CustomerDAO;
import com.crud.modules.customers.DTO.CustomerResponse;
import com.crud.modules.customers.entity.Customer;

@ExtendWith(SpringExtension.class)
public class FindCustomerTest {
  @Mock
  private CustomerDAO customerDAO;
  @InjectMocks
  private FindCustomer findCustomer;

  private Customer customer;

  @BeforeEach
  public void setup() {
    customer = new Customer();
    customer.setNumberAccount(123);
    customer.setId(UUID.randomUUID().toString());
    customer.setName("Test User");
    customer.setCpf("12345678901");
    customer.setAge(25);
  }

  @Test
  public void testFindByCpf_Success() throws BadRequestClient {

    when(customerDAO.findByCpf(customer.getCpf())).thenReturn(customer);

    CustomerResponse customerTest = findCustomer.findByCpf(customer.getCpf());

    verify(customerDAO, times(1)).findByCpf(any());
    assertEquals(customer.getId(), customerTest.getId());
    assertEquals(customer.getName(), customerTest.getName());
    assertEquals(customer.getAge(), customerTest.getAge());
  }

  @Test
  public void testFindByCpf_NotFound() {
    when(customerDAO.findByCpf(customer.getCpf())).thenReturn(null);
    assertThrows(BadRequestClient.class, () -> findCustomer.findByCpf(customer.getCpf()));
  }

  @Test
  public void testFindById_Success() throws BadRequestClient {

    when(customerDAO.findById(customer.getId())).thenReturn(customer);

    CustomerResponse actualResponse = findCustomer.findById(customer.getId());

    verify(customerDAO, times(1)).findById(any());
    assertEquals(customer.getId(), actualResponse.getId());
    assertEquals(customer.getName(), actualResponse.getName());
    assertEquals(customer.getAge(), actualResponse.getAge());
  }

  @Test
  public void testFindById_NotFound() {

    when(customerDAO.findById(customer.getId())).thenReturn(null);

    assertThrows(BadRequestClient.class, () -> findCustomer.findById(customer.getId()));
  }
}