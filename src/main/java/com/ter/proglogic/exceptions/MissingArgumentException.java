package com.ter.proglogic.exceptions;

/**
 * Custom exception thrown when a required argument is missing.
 */
public class MissingArgumentException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new MissingArgumentException with the specified detail message.
     *
     * @param message the detail message
     */
    public MissingArgumentException(String message) {
        super(message);
    }
}
