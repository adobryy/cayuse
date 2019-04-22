package com.cayuse.challenge.exceptions;

public class InvalidInputException extends RuntimeException{
    private static final long serialVersionUID = 7572076970214775188L;

    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }

}
