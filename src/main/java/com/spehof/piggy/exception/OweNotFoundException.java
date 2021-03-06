package com.spehof.piggy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Spehof
 * @created 18/04/2021
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class OweNotFoundException extends RuntimeException {

    public OweNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
