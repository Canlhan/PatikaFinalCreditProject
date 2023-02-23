package com.can.PatikaFinalCreditProject.service;

import com.can.PatikaFinalCreditProject.dto.RequestDto.CustomerRequestDto;
import com.can.PatikaFinalCreditProject.dto.ResponseDto.CustomerResponseDto;
import com.can.PatikaFinalCreditProject.entity.Customer;
import com.can.PatikaFinalCreditProject.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerResponseDto> getAll() {
        List<CustomerResponseDto> customers=new ArrayList<>();

        customerRepository.findAll().stream()
                .forEach(customer -> customers.add(modelMapper.map(customer,CustomerResponseDto.class)));


        return customers ;
    }

    @Override
    public CustomerResponseDto get(Long id) {

        CustomerResponseDto customerResponseDto=modelMapper.map(customerRepository.findById(id).get(),CustomerResponseDto.class);

        return customerResponseDto;
    }

    @Override
    public CustomerResponseDto save(CustomerRequestDto customer) {

        Customer customerAdded=customerRepository.save(modelMapper.map(customer,Customer.class));
        log.info("customer added: "+customerAdded);
        CustomerResponseDto customerResponseDto=modelMapper.map(customerAdded,CustomerResponseDto.class);
        log.info("customer responde dto "+customerResponseDto);
        return customerResponseDto;
    }

    @Override
    public CustomerResponseDto delete(CustomerRequestDto customer) {
        Customer deletedCustomer=customerRepository.findCustomerByIdentifyNo(customer.getIdentifyNo());

        customerRepository.delete(deletedCustomer);
        CustomerResponseDto customerResponseDto=modelMapper.map(deletedCustomer,CustomerResponseDto.class);

        return customerResponseDto ;
    }

    /**
     * This method convert Customer object to CustomerResponse Object
     * @param customer
     * @return List of CustomerResponse objects
     */
    private CustomerResponseDto convertCustomerToDto(Customer customer){
            CustomerResponseDto customerResponseDto=CustomerResponseDto.builder()
                    .customerId(customer.getCustomerId())
                    .quarantee(customer.getQuarantee())
                    .creditScore(customer.getCreditScore())
                    .birthDate(customer.getBirthDate())
                    .loanApplications(customer.getLoanApplications())
                    .build();


        return customerResponseDto;
    }

    private CustomerRequestDto convertCustomerToCustomerRequest(){
        return null;
    }
}
