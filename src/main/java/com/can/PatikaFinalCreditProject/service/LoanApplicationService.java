package com.can.PatikaFinalCreditProject.service;

import com.can.PatikaFinalCreditProject.entity.LoanApplication;

import java.util.List;

public interface LoanApplicationService
{

    List<LoanApplication> getLoanApplicationByCustomerId(Long customerId);

    LoanApplication save(LoanApplication loanApplication);

    LoanApplication get(Long id);
}
