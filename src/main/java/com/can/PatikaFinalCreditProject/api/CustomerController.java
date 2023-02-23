package com.can.PatikaFinalCreditProject.api;

import com.can.PatikaFinalCreditProject.dto.RequestDto.CustomerRequestDto;
import com.can.PatikaFinalCreditProject.dto.ResponseDto.CustomerResponseDto;
import com.can.PatikaFinalCreditProject.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CustomerController
{

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CustomerResponseDto>> getAll(){


        List<CustomerResponseDto> customers=customerService.getAll();

        return ResponseEntity.ok(customers);



    }

    @PostMapping("/")
    public ResponseEntity<CustomerResponseDto> addCustomer(@RequestBody CustomerRequestDto customerRequestDto){


        CustomerResponseDto customerResponseDto=customerService.save(customerRequestDto);

        return ResponseEntity.ok(customerResponseDto);

    }




}
