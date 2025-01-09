package com.crud.usecase.customers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.crud.modules.customers.DAO.CustomerDAO;
import com.crud.modules.customers.DTO.CustomerResponse;
import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.usecase.ListCustomer;

@ExtendWith(SpringExtension.class)
public class ListCustomerUnitTest {
  @Mock
  private CustomerDAO customerDAO;
  @InjectMocks
  private ListCustomer listAllCustomer;

  @Test
  @DisplayName("should all customer")
  void ListAllCustomer() throws Exception {
    Customer customer = new Customer();
    customer.setNumberAccount(123);
    customer.setId(UUID.randomUUID().toString());
    customer.setName("Test User");
    customer.setCpf("12345678901");
    customer.setAge(25);

    when(customerDAO.findAll()).thenReturn(List.of(customer));

    List<CustomerResponse> listAllCustomerResponse = listAllCustomer.execute();

    verify(customerDAO, times(1)).findAll();
    assertEquals(1, listAllCustomerResponse.size());
  }
}
