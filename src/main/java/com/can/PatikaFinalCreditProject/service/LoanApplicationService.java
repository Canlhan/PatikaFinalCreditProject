package com.can.PatikaFinalCreditProject.service;

import com.can.PatikaFinalCreditProject.dto.RequestDto.LoanApplicationRequest;
import com.can.PatikaFinalCreditProject.dto.ResponseDto.LoanApplicationResponse;
import com.can.PatikaFinalCreditProject.entity.Customer;
import com.can.PatikaFinalCreditProject.entity.LoanApplication;

import java.time.LocalDate;
import java.util.List;

public interface LoanApplicationService
{

    List<LoanApplicationResponse> getLoanApplicationByCustomerId(Long customerId);

    LoanApplicationResponse save(LoanApplicationRequest loanApplication);

    LoanApplicationResponse get(Long id);

    List<LoanApplicationResponse> getAll();

    List<LoanApplicationResponse> getLoanApplicationsByIdentifyAndBirhdate(String identifyNo, LocalDate localDate);
}
