package com.can.PatikaFinalCreditProject.service;

import com.can.PatikaFinalCreditProject.dto.RequestDto.CustomerRequestDto;
import com.can.PatikaFinalCreditProject.dto.ResponseDto.CustomerResponseDto;
import com.can.PatikaFinalCreditProject.entity.Customer;

import java.util.List;

public interface CustomerService
{
    List<CustomerResponseDto> getAll();

    CustomerResponseDto get(Long id);

    CustomerResponseDto save(CustomerRequestDto customer);

    CustomerResponseDto delete(CustomerRequestDto customer);

    CustomerResponseDto update(Customer customer);
    boolean isSalaryBetween500And1000(Long id);
    boolean isSalaryOverLimit(Long id,Long limit);



}
