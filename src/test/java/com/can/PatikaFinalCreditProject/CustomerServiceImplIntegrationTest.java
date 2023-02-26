package com.can.PatikaFinalCreditProject;


import com.can.PatikaFinalCreditProject.dto.RequestDto.CustomerRequestDto;
import com.can.PatikaFinalCreditProject.dto.ResponseDto.CustomerResponseDto;
import com.can.PatikaFinalCreditProject.service.CustomerService;

import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerServiceImplIntegrationTest
{
    @Autowired
    private CustomerService customerService;

    @Test
    public void saveCustomer(){
        CustomerRequestDto  customerRequestDto=new CustomerRequestDto();
        customerRequestDto.setFirstName("can");
        customerRequestDto.setIdentifyNo("34567890654");
        customerRequestDto.setTelephonNumber("5434057459");

        CustomerResponseDto customerResponseDto=customerService.save(customerRequestDto);

        assertSame(customerResponseDto.getFirstName(), customerRequestDto.getFirstName());
    }
}
