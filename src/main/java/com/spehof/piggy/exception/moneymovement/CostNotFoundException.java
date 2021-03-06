package com.spehof.piggy.exception.moneymovement;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Spehof
 * @created 18/04/2021
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CostNotFoundException extends RuntimeException {

    public CostNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
