package com.can.PatikaFinalCreditProject.service;

import com.can.PatikaFinalCreditProject.entity.CreditScore;
import com.can.PatikaFinalCreditProject.repository.CreditScoreRepository;
import org.springframework.stereotype.Service;

@Service
public class CreditScoreServiceImpl implements CreditScoreService{


    private final CreditScoreRepository creditScoreRepository;

    public CreditScoreServiceImpl(CreditScoreRepository creditScoreRepository) {
        this.creditScoreRepository = creditScoreRepository;
    }

    @Override
    public CreditScore get(Long id) {

        return creditScoreRepository.findById(id).get();
    }

    @Override
    public CreditScore save(CreditScore creditScore) {

        return creditScoreRepository.save(creditScore);
    }


    @Override
    public boolean isCreditScoreLessThanLimit(CreditScore score, Long limit) {

        if(score.getCreditScore()<limit){
            return true;
        }
        return false;
    }

    @Override
    public boolean isCreditScoreMoreThanLimit(CreditScore score, Long limit) {

        if(score.getCreditScore()>limit){
            return true;
        }
        return false;
    }
}
