package com.restaurent.app.exception;

/**
 * @author Kanchana_m
 */

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
