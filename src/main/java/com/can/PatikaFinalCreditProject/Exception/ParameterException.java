package com.can.PatikaFinalCreditProject.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParameterException extends RuntimeException{

    private String message;

    public ParameterException(String message, String message1) {
        super(message);

    }
}
