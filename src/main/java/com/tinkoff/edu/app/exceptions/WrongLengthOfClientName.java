package com.tinkoff.edu.app.exceptions;

public class WrongLengthOfClientName extends RuntimeException {

    public WrongLengthOfClientName() {
        super();
    }

    public WrongLengthOfClientName(String s, Throwable throwable) {
        super(s, throwable);
    }
}
