package com.can.PatikaFinalCreditProject.service;

import com.can.PatikaFinalCreditProject.entity.LoanApplication;
import com.can.PatikaFinalCreditProject.repository.LoanApplicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LoanApplicationServiceImpl implements LoanApplicationService{

    private final LoanApplicationRepository loanApplicationRepository;

    public LoanApplicationServiceImpl(LoanApplicationRepository loanApplicationRepository) {
        this.loanApplicationRepository = loanApplicationRepository;
    }

    @Override
    public List<LoanApplication> getLoanApplicationByCustomerId(Long customerId) {
        List<LoanApplication> loanApplicationsByCustomerId=loanApplicationRepository.findsByLoanApplicationByCustomerId(customerId);
        log.info("loan application by customer id: "+loanApplicationsByCustomerId);
        return loanApplicationsByCustomerId;
    }

    @Override
    public LoanApplication save(LoanApplication loanApplication) {

        return loanApplicationRepository.save(loanApplication);
    }

    @Override
    public LoanApplication get(Long id) {
        LoanApplication loanApplication=loanApplicationRepository.findById(id).get();
        return loanApplication;
    }
}
