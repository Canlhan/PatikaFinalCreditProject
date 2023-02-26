package com.can.PatikaFinalCreditProject.service;

import com.can.PatikaFinalCreditProject.dto.RequestDto.LoanApplicationRequest;
import com.can.PatikaFinalCreditProject.dto.ResponseDto.LoanApplicationResponse;
import com.can.PatikaFinalCreditProject.entity.CreditScore;
import com.can.PatikaFinalCreditProject.entity.Customer;
import com.can.PatikaFinalCreditProject.entity.LoanApplication;
import com.can.PatikaFinalCreditProject.repository.LoanApplicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LoanApplicationServiceImpl implements LoanApplicationService{

    private final LoanApplicationRepository loanApplicationRepository;
    private final CreditScoreService creditScoreService;
    private final CustomerService customerService;

    private final Long TWENTY_THOUSAND=20_000L;
    private final int CREDIT_LIMIT_MULTIPLIER=4;

    private final NotificationsService smsService;
    private final ModelMapper modelMapper;

    public LoanApplicationServiceImpl(LoanApplicationRepository loanApplicationRepository, CreditScoreService creditScoreService, CustomerService customerService, SmsService smsService, ModelMapper modelMapper) {
        this.loanApplicationRepository = loanApplicationRepository;
        this.creditScoreService = creditScoreService;
        this.customerService = customerService;
        this.smsService = smsService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<LoanApplicationResponse> getLoanApplicationByCustomerId(Long customerId) {
        List<LoanApplicationResponse> loanApplicationsByCustomerId=new ArrayList<>();

        loanApplicationRepository.findAllByCustomer_CustomerId(customerId).forEach(loanApplication ->
                        loanApplicationsByCustomerId.add(modelMapper.map(loanApplication,LoanApplicationResponse.class)));
        log.info("loan application by customer id: "+loanApplicationsByCustomerId);
        return loanApplicationsByCustomerId;
    }

    @Override
    public LoanApplicationResponse save(LoanApplicationRequest loanApplicationRequest) {

        LoanApplication loanApplication=modelMapper.map(loanApplicationRequest,LoanApplication.class);
        Customer customer=loanApplicationRequest.getCustomer();
        CreditScore score=customer.getCreditScore();

        isCreditScoreNotEnough(loanApplication,score,customer);
        isCreditScoreBetween500And1000AndSalaryLess5000(loanApplication,score,customer);
        isCreditBetween500And1000AndSalaryBetween5000And1000(score,loanApplication,customer);
        isCreditBetween500And1000AndSalaryMore10_000(customer,score,loanApplication);
        isCreditScoreMoreOrEqual1_000O(score,customer,loanApplication);

        LoanApplication addedLoanApplication=loanApplicationRepository.save(loanApplication);
        LoanApplicationResponse loanApplicationResponse=modelMapper.map(addedLoanApplication,LoanApplicationResponse.class);

        customer.setLoanApplications(List.of(loanApplication));


        customerService.update(customer);
       smsService.sendNotifications(customer);
        return loanApplicationResponse;
    }

    @Override
    public LoanApplicationResponse get(Long id) {
        LoanApplication loanApplication=loanApplicationRepository.findById(id).get();
        LoanApplicationResponse loanApplicationResponse=modelMapper.map(loanApplication,LoanApplicationResponse.class);
        return loanApplicationResponse;
    }





    @Override
    public List<LoanApplicationResponse> getAll() {

        List<LoanApplicationResponse> loanApplicationResponses=new ArrayList<>();

        loanApplicationRepository.findAll().forEach(loanApplication ->
                loanApplicationResponses.add(modelMapper.map(loanApplication,LoanApplicationResponse.class))
        );


        return loanApplicationResponses;
    }



    @Override
    public List<LoanApplicationResponse> getLoanApplicationsByIdentifyAndBirhdate(String identifyNo, LocalDate localDate) {
        List<LoanApplicationResponse> loanApplicationResponses=new ArrayList<>();

        List<LoanApplication> loanApplications=loanApplicationRepository.findAllByCustomer_IdentifyNoAndCustomer_BirthDate(identifyNo,localDate);

        loanApplications.forEach(loanApplication -> loanApplicationResponses.add(modelMapper.map(loanApplication,LoanApplicationResponse.class)));

        return  loanApplicationResponses;
    }


    private void isCreditScoreNotEnough(LoanApplication loanApplication,CreditScore score,Customer customer){

        if(creditScoreService.isCreditScoreLessThanLimit(score,500l)){
            loanApplication.setApproval(false);
        }

    }
    private void isCreditScoreBetween500And1000AndSalaryLess5000(LoanApplication loanApplication,CreditScore score,Customer customer){
        boolean isCreditValid=creditScoreService.isCreditScoreLessThanLimit(score,1_000l)&&
                creditScoreService.isCreditScoreMoreThanLimit(score,500l);

        if (isCreditValid && customer.getSalary()<5000 ) {
            loanApplication.setApproval(true);

            long quarantee=customer.getQuarantee();
            if(quarantee!=0){
                Long addedAmount=(quarantee*10)/100;
                Long creditAmount=addedAmount+10_000;
                loanApplication.setCreditLimit(creditAmount);
            }
            else{
                loanApplication.setCreditLimit(10_000l);
            }

        }
    }

    private void isCreditBetween500And1000AndSalaryBetween5000And1000(CreditScore score,LoanApplication loanApplication,
                                                                      Customer customer){

        boolean isCreditValid=creditScoreService.isCreditScoreLessThanLimit(score,1_000l)&&
                creditScoreService.isCreditScoreMoreThanLimit(score,500l);

        if (isCreditValid &&customerService.isSalaryBetween500And1000(customer.getCustomerId())  ) {

            loanApplication.setApproval(true);
            if(customer.getQuarantee()!=0){
                long amountOfGuarantee= (customer.getQuarantee()*20)/100+TWENTY_THOUSAND;
                loanApplication.setCreditLimit(amountOfGuarantee);
            }
            else{
                loanApplication.setCreditLimit(TWENTY_THOUSAND);
            }

        }
    }
    private void isCreditBetween500And1000AndSalaryMore10_000(Customer customer,CreditScore score,LoanApplication loanApplication){

        boolean isCreditValid=creditScoreService.isCreditScoreLessThanLimit(score,1_000l)&&
                creditScoreService.isCreditScoreMoreThanLimit(score,500l);

        boolean isSalaryEnough=customerService.isSalaryOverLimit(customer.getCustomerId(),10_000l);

        if( isCreditValid && isSalaryEnough)
        {
            loanApplication.setApproval(true);
            loanApplication.setCustomer(customer);
            long amountOfCreditLimit=(customer.getSalary()*CREDIT_LIMIT_MULTIPLIER)/2;
            if(customer.getQuarantee()!=0){
                long ratioOfGuarantee=(customer.getQuarantee()*25)/100;
                loanApplication.setCreditLimit(amountOfCreditLimit+ratioOfGuarantee);
            }
            else{
                loanApplication.setCreditLimit(amountOfCreditLimit);
            }
        }
    }

    private void isCreditScoreMoreOrEqual1_000O(CreditScore creditScore,Customer customer,LoanApplication loanApplication){
        boolean isCreditMore1_000=creditScoreService.isCreditScoreMoreThanLimit(creditScore,999l);
        if(isCreditMore1_000){
            loanApplication.setApproval(true);
            long limitOfCreditLimit=customer.getSalary()*CREDIT_LIMIT_MULTIPLIER;

            if(customer.getQuarantee()!=0){
                long ratioOfGuarantee=customer.getQuarantee()*50/100;

                loanApplication.setCreditLimit(limitOfCreditLimit+ratioOfGuarantee);
            }else{
                loanApplication.setCreditLimit(limitOfCreditLimit);
            }
        }
    }
}
