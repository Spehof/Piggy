package com.spehof.piggy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.swing.plaf.PanelUI;

/**
 * @author Spehof
 * @created 18/04/2021
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class GoalNotFoundException extends RuntimeException {

    public GoalNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
