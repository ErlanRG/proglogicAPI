package com.ter.proglogic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

/**
 * Global exception handler to manage exceptions across the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles DuplicateValueException and returns an appropriate ResponseEntity with an ErrorObject.
     *
     * @param ex the DuplicateValueException to handle
     * @return a ResponseEntity containing an ErrorObject with details about the exception
     */
    @ExceptionHandler(DuplicateValueException.class)
    public ResponseEntity<ErrorObject> handleDuplicateValueException(DuplicateValueException ex) {
        HttpStatus conflict = HttpStatus.CONFLICT;
        ErrorObject errorObject = new ErrorObject(ex.getClass().getSimpleName(), conflict.value(), ex.getMessage(), new Date());
        return new ResponseEntity<>(errorObject, conflict);
    }

    /**
     * Handles MissingArgumentException and returns an appropriate ResponseEntity with an ErrorObject.
     *
     * @param ex the MissingArgumentException to handle
     * @return a ResponseEntity containing an ErrorObject with details about the exception
     */
    @ExceptionHandler(MissingArgumentException.class)
    public ResponseEntity<ErrorObject> handleMissingArgumentException(MissingArgumentException ex) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ErrorObject errorObject = new ErrorObject(ex.getClass().getSimpleName(), badRequest.value(), ex.getMessage(), new Date());
        return new ResponseEntity<>(errorObject, badRequest);
    }

    /**
     * Handles InvalidAnnualReviewException and returns an appropriate ResponseEntity with an ErrorObject.
     *
     * @param ex the InvalidAnnualReviewException to handle
     * @return a ResponseEntity containing an ErrorObject with details about the exception
     */
    @ExceptionHandler(InvalidAnnualReviewException.class)
    public ResponseEntity<ErrorObject> handleInvalidAnnualReviewException(InvalidAnnualReviewException ex) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ErrorObject errorObject = new ErrorObject(ex.getClass().getSimpleName(), badRequest.value(), ex.getMessage(), new Date());
        return new ResponseEntity<>(errorObject, badRequest);
    }

    /**
     * Handles NotFoundException and returns an appropriate ResponseEntity with an ErrorObject.
     *
     * @param ex the NotFoundException to handle
     * @return a ResponseEntity containing an ErrorObject with details about the exception
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorObject> handleNotFoundException(NotFoundException ex) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ErrorObject errorObject = new ErrorObject(ex.getClass().getSimpleName(), notFound.value(), ex.getMessage(), new Date());
        return new ResponseEntity<>(errorObject, notFound);
    }
}
