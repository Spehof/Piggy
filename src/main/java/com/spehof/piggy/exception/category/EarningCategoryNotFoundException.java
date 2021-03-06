package com.spehof.piggy.exception.category;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Spehof
 * @created 13/04/2021
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EarningCategoryNotFoundException extends RuntimeException{

    public EarningCategoryNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
