package com.spehof.piggy.exception.trading;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Spehof
 * @created 20/04/2021
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PortfolioNotFoundException extends RuntimeException {

    public PortfolioNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
