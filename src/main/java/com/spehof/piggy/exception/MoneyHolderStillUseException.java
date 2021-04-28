package com.spehof.piggy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Spehof
 * @created 28/04/2021
 */
@ResponseStatus(HttpStatus.IM_USED)
public class MoneyHolderStillUseException extends RuntimeException {
    public MoneyHolderStillUseException(String errorMessage) {
        super(errorMessage);
    }
}
