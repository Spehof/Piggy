package com.spehof.piggy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Spehof
 * @created 20/04/2021
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class BrokerAccountNotFoundException extends RuntimeException {

    public BrokerAccountNotFoundException(String errorMessage){
        super(errorMessage);
    }
}

