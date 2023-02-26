package com.can.PatikaFinalCreditProject.service;

import com.can.PatikaFinalCreditProject.dto.RequestDto.CustomerRequestDto;
import com.can.PatikaFinalCreditProject.dto.ResponseDto.CustomerResponseDto;
import com.can.PatikaFinalCreditProject.entity.CreditScore;
import com.can.PatikaFinalCreditProject.entity.Customer;
import com.can.PatikaFinalCreditProject.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    private final CreditScoreService creditScoreService;


    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CreditScoreService creditScoreService, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.creditScoreService = creditScoreService;
        this.modelMapper = modelMapper;
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

        Random random =new Random();
        long creditScore=random.nextLong(1001);

        CreditScore score=creditScoreService.save(CreditScore.builder().creditScore(creditScore).build());
        Customer addCustomer=modelMapper.map(customer,Customer.class);
        addCustomer.setCreditScore(score);
        Customer customerAdded=customerRepository.save(addCustomer);

        CustomerResponseDto customerResponseDto=modelMapper.map(customerAdded,CustomerResponseDto.class);

        return customerResponseDto;
    }

    @Override
    public CustomerResponseDto delete(CustomerRequestDto customer) {
        Customer deletedCustomer=customerRepository.findCustomerByIdentifyNo(customer.getIdentifyNo());

        customerRepository.delete(deletedCustomer);
        CustomerResponseDto customerResponseDto=modelMapper.map(deletedCustomer,CustomerResponseDto.class);

        return customerResponseDto ;
    }

    @Override
    public CustomerResponseDto update(Customer customer) {

        customerRepository.save(customer);

        CustomerResponseDto customerResponseDto=modelMapper.map(customer,CustomerResponseDto.class);
        return customerResponseDto;
    }

    @Override
    public boolean isSalaryBetween500And1000(Long id) {
        CustomerResponseDto customer=get(id);
        long salary=customer.getSalary();
        if(salary>5000 && salary<10000){

            return true;
        }
        return false;
    }

    @Override
    public boolean isSalaryOverLimit(Long id, Long limit) {
        CustomerResponseDto customerResponseDto=get(id);
        if(customerResponseDto.getSalary()>=limit){
            return true;
        }
        return false;
    }


}
