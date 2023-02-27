package com.can.PatikaFinalCreditProject.api;

import com.can.PatikaFinalCreditProject.Exception.ParameterException;
import com.can.PatikaFinalCreditProject.dto.RequestDto.CustomerRequestDto;
import com.can.PatikaFinalCreditProject.dto.ResponseDto.CustomerResponseDto;
import com.can.PatikaFinalCreditProject.entity.Customer;
import com.can.PatikaFinalCreditProject.service.CustomerService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/customer")
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
    public ResponseEntity<CustomerResponseDto> addCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto)
    throws ParameterException {


        CustomerResponseDto customerResponseDto=customerService.save(customerRequestDto);

        return ResponseEntity.ok(customerResponseDto);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getCustomerId(@PathVariable Long id){


        CustomerResponseDto customers=customerService.get(id);

        return ResponseEntity.ok(customers);



    }
    @PutMapping("/update")
    public ResponseEntity<CustomerResponseDto> updateCustomer(@RequestBody Customer customer){

        CustomerResponseDto customers=customerService.update(customer);

        return ResponseEntity.ok(customers);



    }



}
