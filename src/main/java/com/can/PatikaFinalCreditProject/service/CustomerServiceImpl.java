package com.can.PatikaFinalCreditProject.service;

import com.can.PatikaFinalCreditProject.entity.Customer;
import com.can.PatikaFinalCreditProject.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAll() {

        return  customerRepository.findAll();
    }

    @Override
    public Customer get(Long id) {

        return customerRepository.findById(id).get();
    }

    @Override
    public Customer save(Customer customer) {

        return customerRepository.save(customer);
    }

    @Override
    public Customer delete(Customer customer) {
        Customer deletedCustomer=customerRepository.findById(customer.getCustomerId()).get();
        customerRepository.delete(customer);

        return deletedCustomer ;
    }
}
