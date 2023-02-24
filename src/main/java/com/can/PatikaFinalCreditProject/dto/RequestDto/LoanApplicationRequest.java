package com.can.PatikaFinalCreditProject.dto.RequestDto;

import com.can.PatikaFinalCreditProject.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoanApplicationRequest
{


    private Customer customer;

}
