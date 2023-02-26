package com.can.PatikaFinalCreditProject.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerNotFoundException extends RuntimeException
{
    private String message;

    public CustomerNotFoundException(String message, String message1) {
        super(message);

    }
}
