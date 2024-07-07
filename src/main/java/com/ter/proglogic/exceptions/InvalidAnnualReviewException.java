package com.ter.proglogic.exceptions;

/**
 * Custom exception thrown when an annual review is invalid.
 */
public class InvalidAnnualReviewException extends RuntimeException {
    /**
     * Constructs a new InvalidAnnualReviewException with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidAnnualReviewException(String message) {
        super(message);
    }
}
