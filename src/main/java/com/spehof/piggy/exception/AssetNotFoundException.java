package com.spehof.piggy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Spehof
 * @created 20/04/2021
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AssetNotFoundException extends RuntimeException {

    public AssetNotFoundException(String errorMessage){
        super(errorMessage);

    }
}
