package com.tinkoff.edu.app.exceptions;

public class WrongLengthOfClientNameException extends RuntimeException {

    public WrongLengthOfClientNameException() {
        super();
    }

    public WrongLengthOfClientNameException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
