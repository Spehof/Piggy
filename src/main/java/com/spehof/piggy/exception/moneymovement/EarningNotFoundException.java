package com.spehof.piggy.exception.moneymovement;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Spehof
 * @created 18/04/2021
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EarningNotFoundException extends RuntimeException {

    public EarningNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
