package com.can.PatikaFinalCreditProject.service;

import com.can.PatikaFinalCreditProject.entity.Customer;

import java.util.List;

public interface CustomerService
{
    List<Customer> getAll();

    Customer get(Long id);

    Customer save(Customer customer);

    Customer delete(Customer customer);

}
