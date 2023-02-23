package com.can.PatikaFinalCreditProject.dto.ResponseDto;

import com.can.PatikaFinalCreditProject.entity.CreditScore;
import com.can.PatikaFinalCreditProject.entity.LoanApplication;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CustomerResponseDto
{

    private Long customerId;


    private String firstName;


    private String lastName;


    private String telephonNumber;


    private LocalDate birthDate;


    private Long quarantee;


    private CreditScore creditScore;



    private Set<LoanApplication> loanApplications;
}
