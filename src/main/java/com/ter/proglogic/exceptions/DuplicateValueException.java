package com.ter.proglogic.exceptions;

/**
 * Custom exception thrown when a duplicate value is encountered.
 */
public class DuplicateValueException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new DuplicateValueException with the specified detail message.
     *
     * @param message the detail message
     */
    public DuplicateValueException(String message) {
        super(message);
    }
}
