package com.ter.proglogic.exceptions;

public class MissingArgumentException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MissingArgumentException(String message) {
        super(message);
    }
}
