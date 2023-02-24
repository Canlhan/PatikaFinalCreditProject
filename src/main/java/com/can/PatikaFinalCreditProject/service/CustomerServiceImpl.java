package com.can.PatikaFinalCreditProject.service;

import com.can.PatikaFinalCreditProject.dto.RequestDto.CustomerRequestDto;
import com.can.PatikaFinalCreditProject.dto.ResponseDto.CustomerResponseDto;
import com.can.PatikaFinalCreditProject.entity.CreditScore;
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

    private final CreditScoreService creditScoreService;

    @Autowired
    private ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CreditScoreService creditScoreService) {
        this.customerRepository = customerRepository;
        this.creditScoreService = creditScoreService;
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

        CreditScore score=creditScoreService.save(CreditScore.builder().creditScore(501l).build());
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
