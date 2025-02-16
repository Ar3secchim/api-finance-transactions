package com.crud.modules.customers.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.modules.customers.DTO.BalanceResquest;
import com.crud.modules.customers.DTO.CustomerRequest;
import com.crud.modules.customers.DTO.CustomerResponse;
import com.crud.modules.customers.usecase.FindCustomer;
import com.crud.modules.customers.usecase.ListCustomer;
import com.crud.modules.customers.usecase.RegisterCustomer;
import com.crud.modules.customers.usecase.UpdateBalanceCustomer;
import com.crud.utils.CustomerConvert;
import com.crud.utils.ResponseMessage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
        @Autowired
        RegisterCustomer registerCustomer;
        @Autowired
        FindCustomer findCustomer;
        @Autowired
        ListCustomer listCustomer;
        @Autowired
        UpdateBalanceCustomer updateBalance;

        @Operation(summary = "Create a customer", description = "Returns a customer")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Successfully retrieved"),
                        @ApiResponse(responseCode = "400", description = "Not possible create customer")
        })
        @PostMapping
        public ResponseEntity<CustomerResponse> createCustomer(
                        @Valid @RequestBody CustomerRequest customerRequest) throws Exception {
                CustomerResponse customerResponse = registerCustomer.execute(CustomerConvert.toEntity(customerRequest));
                return ResponseEntity.created(
                                URI.create("/" + customerResponse.getId()))
                                .body(customerResponse);
        }

        @Operation(summary = "Get all customer", description = "Returns a list customer")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
                        @ApiResponse(responseCode = "400", description = "Not possible get all customers")
        })
        @GetMapping
        public ResponseEntity<List<CustomerResponse>> getAllCustomer() throws Exception {
                return ResponseEntity.ok(listCustomer.execute());
        }

        @Operation(summary = "Get customer by id", description = "Return customer")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
                        @ApiResponse(responseCode = "404", description = "Not possible get customers")
        })
        @GetMapping("/{id}")
        public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable String id) throws Exception {
                return ResponseEntity.ok(findCustomer.findById(id));
        }

        @Operation(summary = "Create new balance customer", description = "Returns msg success")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
                        @ApiResponse(responseCode = "400", description = "Not possible update customer")
        })
        @PostMapping("/balance")
        public ResponseEntity<ResponseMessage> updateBalance(
                        @Valid @RequestBody BalanceResquest BalanceRequest) throws Exception {

                ResponseMessage response = updateBalance.execute(BalanceRequest);

                return ResponseEntity.ok(response);
        }
}
