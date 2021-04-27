package com.spehof.piggy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Spehof
 * @created 08/04/2021
 */
@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
public class InvalidCreateUserException extends RuntimeException{

    public InvalidCreateUserException(String errorMessage){
        super(errorMessage);
    }
}
