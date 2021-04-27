package com.spehof.piggy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Spehof
 * @created 13/04/2021
 */
// TODO write
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CostCategoryNotFoundException extends RuntimeException{

    public CostCategoryNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
