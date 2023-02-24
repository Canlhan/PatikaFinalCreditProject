package com.can.PatikaFinalCreditProject.api;

import com.can.PatikaFinalCreditProject.dto.RequestDto.LoanApplicationRequest;
import com.can.PatikaFinalCreditProject.dto.ResponseDto.LoanApplicationResponse;
import com.can.PatikaFinalCreditProject.entity.Customer;
import com.can.PatikaFinalCreditProject.service.LoanApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loan")
public class LoanApplicationController
{

    private final LoanApplicationService loanApplicationService;

    public LoanApplicationController(LoanApplicationService loanApplicationService) {
        this.loanApplicationService = loanApplicationService;
    }

    @GetMapping("/")
    public ResponseEntity<List<LoanApplicationResponse>> getAll(){
        List<LoanApplicationResponse> loanApplicationResponses=loanApplicationService.getAll();

        return ResponseEntity.ok(loanApplicationResponses);

    }

    @PostMapping(path = "/",consumes = "application/json;charset=UTF-8")
    public ResponseEntity<LoanApplicationResponse> save(@RequestBody LoanApplicationRequest loanApplicationRequest){

        LoanApplicationResponse loanApplicationResponse=loanApplicationService.save(loanApplicationRequest);

        return ResponseEntity.ok(loanApplicationResponse);

    }
}
