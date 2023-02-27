package com.can.PatikaFinalCreditProject.dto.ResponseDto;

import com.can.PatikaFinalCreditProject.entity.CreditScore;
import com.can.PatikaFinalCreditProject.entity.LoanApplication;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CustomerResponseDto
{

    private Long customerId;

    private String identifyNo;
    private String firstName;


    private String lastName;


    private String telephonNumber;
    private Long salary;

    private LocalDate birthDate;


    private Long guarantee;


    private CreditScore creditScore;




    private List<LoanApplication> loanApplications;
}
