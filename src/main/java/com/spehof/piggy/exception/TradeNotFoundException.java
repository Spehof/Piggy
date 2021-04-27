package com.spehof.piggy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Spehof
 * @created 21/04/2021
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class TradeNotFoundException extends RuntimeException {

    public TradeNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
