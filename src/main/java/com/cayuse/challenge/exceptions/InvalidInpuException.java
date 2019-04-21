package com.cayuse.challenge.exceptions;

public class InvalidInpuException extends RuntimeException{
    private static final long serialVersionUID = 7572076970214775188L;

    public InvalidInpuException(String message) {
        super(message);
    }

    public InvalidInpuException(String message, Throwable cause) {
        super(message, cause);
    }

}
