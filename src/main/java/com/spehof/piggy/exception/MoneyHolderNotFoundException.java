package com.spehof.piggy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Spehof
 * @created 21/04/2021
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MoneyHolderNotFoundException extends RuntimeException {

    public MoneyHolderNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
