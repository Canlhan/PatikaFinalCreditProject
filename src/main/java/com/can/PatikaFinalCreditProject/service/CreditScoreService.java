package com.can.PatikaFinalCreditProject.service;

import com.can.PatikaFinalCreditProject.entity.CreditScore;

public interface CreditScoreService
{


    CreditScore get(Long id);
    CreditScore save(CreditScore creditScore);
}
