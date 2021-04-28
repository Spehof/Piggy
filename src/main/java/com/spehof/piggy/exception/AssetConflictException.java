package com.spehof.piggy.exception;

/**
 * @author Spehof
 * @created 28/04/2021
 */
public class AssetConflictException extends RuntimeException {
    public AssetConflictException(String errorMessage) {
        super(errorMessage);
    }
}
