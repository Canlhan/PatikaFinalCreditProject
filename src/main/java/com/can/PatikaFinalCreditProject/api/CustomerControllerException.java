package com.can.PatikaFinalCreditProject.api;

import com.can.PatikaFinalCreditProject.Exception.CustomerNotFoundException;
import com.can.PatikaFinalCreditProject.entity.Exception.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerControllerException
{

    private ExceptionResponse exceptionResponse;

    @ExceptionHandler({CustomerNotFoundException.class})
    public ResponseEntity<ExceptionResponse> customerNotFound(){

        exceptionResponse.setMessage("Bilgilerinizi kontrol ediniz");
        return ResponseEntity.badRequest().body(exceptionResponse);
    }


}
