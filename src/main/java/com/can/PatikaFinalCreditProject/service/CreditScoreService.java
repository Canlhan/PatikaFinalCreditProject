package com.can.PatikaFinalCreditProject.service;

import com.can.PatikaFinalCreditProject.entity.CreditScore;

public interface CreditScoreService
{


    CreditScore get(Long id);
    CreditScore save(CreditScore creditScore);

    /**
     * This method compare the creditScore with limit
     * if you want to equal your value to limit then you should do value of limit plus one
     * @param score
     * @param limit
     * @return void
     */
    boolean isCreditScoreLessThanLimit(CreditScore score,Long limit);

    /**
     * This method compare the creditScore with limit
     * if you want to equal your value to limit then you should do value of limit minus one
     * @param score
     * @param limit
     * @return void
     */
    boolean isCreditScoreMoreThanLimit(CreditScore score,Long limit);

}
