package com.can.PatikaFinalCreditProject.dto.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoanApplicationResponse
{

    private boolean isApproval;
    private Long limit;
}
