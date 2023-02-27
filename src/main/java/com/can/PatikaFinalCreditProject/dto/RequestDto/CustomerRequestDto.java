package com.can.PatikaFinalCreditProject.dto.RequestDto;

import com.can.PatikaFinalCreditProject.entity.CreditScore;
import com.can.PatikaFinalCreditProject.entity.LoanApplication;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CustomerRequestDto
{


    private String identifyNo;
    private String firstName;


    private String lastName;


    private String telephonNumber;


    private LocalDate birthDate;
    private Long salary;

    private Long guarantee;


}
