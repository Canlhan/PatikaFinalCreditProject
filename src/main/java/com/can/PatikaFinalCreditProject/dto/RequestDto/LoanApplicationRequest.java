package com.can.PatikaFinalCreditProject.dto.RequestDto;

import com.can.PatikaFinalCreditProject.entity.Customer;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class LoanApplicationRequest
{


    private Customer customer;

}
