package com.can.PatikaFinalCreditProject.service;

import com.can.PatikaFinalCreditProject.dto.RequestDto.CustomerRequestDto;
import com.can.PatikaFinalCreditProject.dto.ResponseDto.CustomerResponseDto;
import com.can.PatikaFinalCreditProject.entity.CreditScore;
import com.can.PatikaFinalCreditProject.entity.Customer;
import com.can.PatikaFinalCreditProject.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceImplTest {


    private CustomerServiceImpl customerService;

    private CustomerRepository customerRepository;


    private  CreditScoreService creditScoreService;

    @Captor
    ArgumentCaptor<CustomerRequestDto> emailCaptor;


    @Mock
    private ModelMapper modelMapper;

    @Before
   public void SetUp(){

        customerRepository=mock(CustomerRepository.class);
        modelMapper=mock(ModelMapper.class);
        creditScoreService=mock(CreditScoreServiceImpl.class);
        customerService=new CustomerServiceImpl(customerRepository,creditScoreService, modelMapper);
    }

    @Test
    public void whenCustomerGetAll(){

        Customer customer=Customer.builder()
                .customerId(1L).birthDate(LocalDate.of(2022,3,12))
                .guarantee(1000L).firstName("test-name").build();

        CustomerResponseDto customerResponseDto=CustomerResponseDto.builder().customerId(1L).birthDate(LocalDate.of(2022,3,12))
                .guarantee(1000L).firstName("test-name").build();

        when(customerRepository.findAll()).thenReturn(List.of(customer));
        when(modelMapper.map(customer,CustomerResponseDto.class)).thenReturn(customerResponseDto);


        List<CustomerResponseDto> customers=customerService.getAll();

        assertEquals(customers.size(),1);





    }

    @Test
    public void whenGetCustomerById(){

        Customer customer=Customer.builder()
                .customerId(1L).birthDate(LocalDate.of(2022,3,12))
                .guarantee(1000L).firstName("test-name").build();

        CustomerResponseDto customerResponseDto=CustomerResponseDto.builder().customerId(1L).birthDate(LocalDate.of(2022,3,12))
                .guarantee(1000L).firstName("test-name").build();
        when(customerRepository.findById(customer.getCustomerId())).thenReturn(Optional.of(customer));

        when(modelMapper.map(customer,CustomerResponseDto.class)).thenReturn(customerResponseDto);

        CustomerResponseDto customerResponseDto1=customerService.get(1L);
        assertEquals(customerResponseDto1.getCustomerId(),1L);
    }


    @Test
    public void testSave(){

        CreditScore score=CreditScore.builder().creditScore(500l).creditId(1L).build();
        Customer customer=Customer.builder()
                .customerId(1L).birthDate(LocalDate.of(2022,3,12))
                .guarantee(1000L).creditScore(score).firstName("test-name").build();


        CustomerRequestDto customerRequestDto= CustomerRequestDto.builder()
                .birthDate(LocalDate.of(2022,3,12))
                .guarantee(1000L).firstName("test-name").build();

        CustomerResponseDto customerResponseDto=CustomerResponseDto.builder().customerId(12L).birthDate(LocalDate.of(2022,3,12))
                .guarantee(1000L).firstName("test-name").build();

       //when
        when(creditScoreService.save(score)).thenReturn(score);
        when(modelMapper.map(customerRequestDto,Customer.class)).thenReturn(customer);

        when(customerRepository.save(customer)).thenReturn(customer);
        when(modelMapper.map(customer,CustomerResponseDto.class)).thenReturn(customerResponseDto);

        //Asssert
        assertSame(customerService.save(customerRequestDto).getFirstName(),"test-name");


    }



    @Test
    public void testDelete(){
        CustomerRequestDto customerRequestDto= CustomerRequestDto.builder()
                .birthDate(LocalDate.of(2022,3,12))
                .identifyNo("34543135456")
                .guarantee(1000L).firstName("test-name").build();


        Customer customer=Customer.builder()
                .customerId(1L).birthDate(LocalDate.of(2022,3,12))
                .identifyNo("34543135456")
                .guarantee(1000L).firstName("test-name").build();

        CustomerResponseDto customerResponseDto=CustomerResponseDto.builder().customerId(1L).birthDate(LocalDate.of(2022,3,12))
                .guarantee(1000L).identifyNo("34543535456").firstName("test-name").build();

        when(customerRepository.findCustomerByIdentifyNo(customerRequestDto.getIdentifyNo())).thenReturn(customer);
       customerRepository.delete(customer);

       when(modelMapper.map(customer,CustomerResponseDto.class)).thenReturn(customerResponseDto);

       assertSame(customerService.delete(customerRequestDto).getIdentifyNo(),"34543535456");


    }


    @Test
    public void testUpdate()
    {
        Customer customer=Customer.builder()
                .customerId(1L).birthDate(LocalDate.of(2022,3,12))
                .identifyNo("34543135456")
                .guarantee(1000L).firstName("test-name").build();
        CustomerResponseDto customerResponseDto=CustomerResponseDto.builder().customerId(1L).birthDate(LocalDate.of(2022,3,12))
                .guarantee(1000L).identifyNo("34543535456").firstName("test-name").build();


        when(customerRepository.save(customer)).thenReturn(customer);
        when(modelMapper.map(customer,CustomerResponseDto.class)).thenReturn(customerResponseDto);

        assertSame(customerService.update(customer),customerResponseDto);
    }

    @Test
    public void testIsSalaryBetween500And1000(){
        CustomerResponseDto customerResponseDto=CustomerResponseDto.builder()
                .salary(6000l).customerId(1L).birthDate(LocalDate.of(2022,3,12))
                .guarantee(1000L).identifyNo("34543535456").firstName("test-name").build();

        Customer customer=Customer.builder()
                .customerId(1L).birthDate(LocalDate.of(2022,3,12))
                .identifyNo("34543135456")
                .guarantee(1000L).firstName("test-name").build();

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(modelMapper.map(customer,CustomerResponseDto.class)).thenReturn(customerResponseDto);
        when(customerService.get(anyLong())).thenReturn(customerResponseDto);


        assertTrue(customerService.isSalaryBetween500And1000(customerResponseDto.getSalary()));

    }
    @Test
    public void testIsNotSalaryBetween500And1000(){
        CustomerResponseDto customerResponseDto=CustomerResponseDto.builder()
                .salary(3000l).customerId(1L).birthDate(LocalDate.of(2022,3,12))
                .guarantee(1000L).identifyNo("34543535456").firstName("test-name").build();

        Customer customer=Customer.builder()
                .customerId(1L).birthDate(LocalDate.of(2022,3,12))
                .identifyNo("34543135456")
                .guarantee(1000L).firstName("test-name").build();

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(modelMapper.map(customer,CustomerResponseDto.class)).thenReturn(customerResponseDto);
        when(customerService.get(anyLong())).thenReturn(customerResponseDto);


        assertFalse(customerService.isSalaryBetween500And1000(customerResponseDto.getSalary()));

    }



}