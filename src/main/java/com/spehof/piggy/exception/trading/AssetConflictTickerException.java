package com.spehof.piggy.exception.trading;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Spehof
 * @created 28/04/2021
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class AssetConflictTickerException extends RuntimeException {
    public AssetConflictTickerException(String errorMessage) {
        super(errorMessage);
    }
}
