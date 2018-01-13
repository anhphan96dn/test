package com.anh.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String s) {
        super(s);
    }

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    protected UserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}