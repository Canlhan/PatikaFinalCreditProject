package com.can.PatikaFinalCreditProject.api;

import com.can.PatikaFinalCreditProject.Exception.CustomerNotFoundException;
import com.can.PatikaFinalCreditProject.entity.Exception.ExceptionResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
public class CustomerControllerException
{

    private ExceptionResponse exceptionResponse;

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ExceptionResponse> customerNotFound(){

        exceptionResponse.setMessage("Bilgilerinizi kontrol ediniz");
        return ResponseEntity.badRequest().body(exceptionResponse);
    }
    @ExceptionHandler({HttpServerErrorException.InternalServerError.class})
    public ResponseEntity<ExceptionResponse> customerServer(){

        exceptionResponse.setMessage("hatalı bir istek ");
        return ResponseEntity.badRequest().body(exceptionResponse);
    }


}
