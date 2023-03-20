package com.can.PatikaFinalCreditProject.utils;

import com.can.PatikaFinalCreditProject.entity.CreditScore;

public class CreditScoreValid
{


    /**
     * This method checks the value between  constraints
     * @param score
     * @param min
     * @param max
     * @return boolean
     */
    public static boolean isCreditBetweenLimits(CreditScore score,long min,long max){

        boolean isCreditValid=isCreditScoreLessThanLimit(score,min)&&
                isCreditScoreMoreThanLimit(score,max);

        return isCreditValid;

    }

    /**
     * This method checks the value less than limit
     * @param score
     * @param limit
     * @return
     */
    public static boolean isCreditScoreLessThanLimit(CreditScore score, Long limit) {

        if(score.getCreditScore()<limit){
            return true;
        }
        return false;
    }


    /**
     * This method checks the value more than limit
     * @param score
     * @param limit
     * @return
     */
    public static boolean isCreditScoreMoreThanLimit(CreditScore score, Long limit) {

        if(score.getCreditScore()>limit){
            return true;
        }
        return false;
    }
}
