package com.cayuse.challenge.exceptions;

public class NoDataAvailableException extends RuntimeException{

    private static final long serialVersionUID = 7572076970214799188L;

    public NoDataAvailableException(String message) {
        super(message);
    }

    public NoDataAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

}
