package com.can.PatikaFinalCreditProject.dto.ResponseDto;

import com.can.PatikaFinalCreditProject.entity.Customer;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class LoanApplicationResponse
{

    private Long loanId;
    private boolean isApproval;
    private Long limit;
    private Customer customer;
}
