package com.spehof.piggy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Spehof
 * @created 15/04/2021
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotificationNotFoundException extends RuntimeException {
}
