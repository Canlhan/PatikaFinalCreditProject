package com.can.PatikaFinalCreditProject.api;

import com.can.PatikaFinalCreditProject.dto.RequestDto.LoanApplicationRequest;
import com.can.PatikaFinalCreditProject.dto.ResponseDto.LoanApplicationResponse;
import com.can.PatikaFinalCreditProject.entity.Customer;
import com.can.PatikaFinalCreditProject.service.LoanApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
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

    @PostMapping(path = "/")
    public ResponseEntity<LoanApplicationResponse> save(@RequestBody LoanApplicationRequest loanApplicationRequest){

        LoanApplicationResponse loanApplicationResponse=loanApplicationService.save(loanApplicationRequest);

        return ResponseEntity.ok(loanApplicationResponse);

    }
    @GetMapping("/{id}")
    public ResponseEntity<List<LoanApplicationResponse>> getLoanApplicationOfCustomer(@PathVariable Long id){

        List<LoanApplicationResponse> loanApplicationResponses=loanApplicationService.getLoanApplicationByCustomerId(id);

        return ResponseEntity.ok(loanApplicationResponses);

    }
    @GetMapping("/get/{id}")
    public ResponseEntity<LoanApplicationResponse> getLoanApplication(@PathVariable Long id){

        LoanApplicationResponse loanApplicationResponses=loanApplicationService.get(id);

        return ResponseEntity.ok(loanApplicationResponses);

    }

    @GetMapping("/search")
    public ResponseEntity< List<LoanApplicationResponse>> getLoanApplicationsDate(@RequestParam String identifyNo, @RequestParam LocalDate localDate){

        List<LoanApplicationResponse> loanApplicationResponses=loanApplicationService.getLoanApplicationsByIdentifyAndBirhdate(identifyNo,localDate);

        return ResponseEntity.ok(loanApplicationResponses);

    }
}
