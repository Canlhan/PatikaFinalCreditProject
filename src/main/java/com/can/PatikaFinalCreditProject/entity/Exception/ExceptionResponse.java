package com.can.PatikaFinalCreditProject.entity.Exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse
{


    private String message;
    private boolean status=false;


}
