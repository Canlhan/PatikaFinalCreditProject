package com.can.PatikaFinalCreditProject.service;

import com.can.PatikaFinalCreditProject.dto.RequestDto.LoanApplicationRequest;
import com.can.PatikaFinalCreditProject.dto.ResponseDto.LoanApplicationResponse;
import com.can.PatikaFinalCreditProject.entity.CreditScore;
import com.can.PatikaFinalCreditProject.entity.Customer;
import com.can.PatikaFinalCreditProject.entity.LoanApplication;
import com.can.PatikaFinalCreditProject.repository.LoanApplicationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.modelmapper.ModelMapper;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class LoanApplicationServiceImplTest {



    @InjectMocks
    private LoanApplicationServiceImpl loanApplicationService;
    @Mock
    private ModelMapper modelMapper;

    @Mock
    private LoanApplicationRepository loanApplicationRepository;

    @Mock
    private CreditScoreServiceImpl creditScoreService;

    @Mock
    private CustomerServiceImpl customerService;

    @Mock
    private SmsServiceImpl smsService;


    @Test
    public void getLoanApplicationByCustomerId() {
        LoanApplicationResponse loanApplicationResponse=LoanApplicationResponse.builder()
                .isApproval(true).limit(10000l).build();

        Customer customer=Customer.builder()
                .customerId(1L).birthDate(LocalDate.of(2022,3,12))
                .quarantee(1000L).firstName("test-name").identifyNo("23434543454").build();

        LoanApplication loanApplication=LoanApplication.builder()
                .loanId(1L).customer(customer).build();

        when(loanApplicationRepository.findAllByCustomer_CustomerId(customer.getCustomerId())).thenReturn(List.of(loanApplication));
        when(modelMapper.map(loanApplication,LoanApplicationResponse.class)).thenReturn(loanApplicationResponse);


        assertEquals(loanApplicationService.getLoanApplicationByCustomerId(1L).size(),1);

    }

    @Test
    public void testSave() {
//        CreditScore score=CreditScore.builder().creditId(1L).creditScore(501l).build();
//        Customer customer=Customer.builder()
//                .customerId(1L).birthDate(LocalDate.of(2022,3,12))
//                .quarantee(1000L).creditScore(score).firstName("test-name").identifyNo("23434543454").build();
//
//        LoanApplicationRequest loanApplicationRequest=LoanApplicationRequest.builder().customer(customer).build();
//
//
//        LoanApplication loanApplication=LoanApplication.builder()
//                .loanId(1L).customer(customer).build();
//
//
//        when(loanApplicationRequest.getCustomer()).thenReturn(customer);
//        when(modelMapper.map(loanApplicationRequest,LoanApplication.class)).thenReturn(loanApplication);
//


    }

    @Test
    public void get()
    {
        LoanApplicationResponse loanApplicationResponse=LoanApplicationResponse.builder()
                .isApproval(true).loanId(1L).limit(10000l).build();

        LoanApplication loanApplication=LoanApplication.builder().loanId(1L).build();

        when(loanApplicationRepository.findById(anyLong())).thenReturn(Optional.of(loanApplication));
        when(modelMapper.map(loanApplication,LoanApplicationResponse.class)).thenReturn(loanApplicationResponse);

        assertEquals(loanApplicationService.get(anyLong()).getLoanId(),loanApplicationResponse.getLoanId());
    }

    @Test
    public void getAll()
    {
        LoanApplicationResponse loanApplicationResponse=LoanApplicationResponse.builder()
                .isApproval(true).loanId(1L).limit(10000l).build();

        LoanApplication loanApplication=LoanApplication.builder().loanId(1L).build();

        when(modelMapper.map(loanApplication,LoanApplicationResponse.class)).thenReturn(loanApplicationResponse);
        when(loanApplicationRepository.findAll()).thenReturn(List.of(loanApplication));


        assertEquals(loanApplicationService.getAll(),List.of(loanApplicationResponse));
    }

    @Test
    public void getLoanApplicationsByIdentifyAndBirhdate()
    {
        LoanApplication loanApplication=LoanApplication.builder().loanId(1L).build();
        Customer customer=Customer.builder()
                .customerId(1L).birthDate(LocalDate.of(2022,3,12))
              .quarantee(1000L).firstName("test-name").loanApplications(List.of(loanApplication)).identifyNo("23434543454").build();

        loanApplication.setCustomer(customer);

        LoanApplicationResponse loanApplicationResponse=LoanApplicationResponse.builder()
                .isApproval(true).loanId(1L).limit(10000l).build();




        when(loanApplicationRepository.findAllByCustomer_IdentifyNoAndCustomer_BirthDate(customer.getIdentifyNo(),customer.getBirthDate()))
                .thenReturn(List.of(loanApplication));

        when(modelMapper.map(loanApplication,LoanApplicationResponse.class)).thenReturn(loanApplicationResponse);

        assertEquals(loanApplicationService.getLoanApplicationsByIdentifyAndBirhdate(customer.getIdentifyNo(),customer.getBirthDate())
                ,List.of(loanApplicationResponse));

    }
}