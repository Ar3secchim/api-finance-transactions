package com.crud.modules.customers.DTO;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {
  @NotBlank
  @Length(min = 3, max = 35)
  private String name;
  @NotNull
  private Integer age;
  @NotBlank
  private String cpf;
}
