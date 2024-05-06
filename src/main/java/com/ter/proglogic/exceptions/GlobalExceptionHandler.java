package com.ter.proglogic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateValueException.class)
    public ResponseEntity<ErrorObject> handleDuplicateValueException(DuplicateValueException ex) {
        HttpStatus conflict = HttpStatus.CONFLICT;
        ErrorObject errorObject = new ErrorObject(ex.getClass().getSimpleName(), conflict.value(), ex.getMessage(), new Date());

        return new ResponseEntity<>(errorObject, conflict);
    }

    @ExceptionHandler(MissingArgumentException.class)
    public ResponseEntity<ErrorObject> handleMissingArgumentException(MissingArgumentException ex) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ErrorObject errorObject = new ErrorObject(ex.getClass().getSimpleName(), badRequest.value(), ex.getMessage(), new Date());

        return new ResponseEntity<>(errorObject, badRequest);
    }

    @ExceptionHandler(InvalidAnnualReviewException.class)
    public ResponseEntity<ErrorObject> handleInvalidAnnualReviewException(InvalidAnnualReviewException ex) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ErrorObject errorObject = new ErrorObject(ex.getClass().getSimpleName(), badRequest.value(), ex.getMessage(), new Date());

        return new ResponseEntity<>(errorObject, badRequest);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorObject> handleNotFoundException(NotFoundException ex) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ErrorObject errorObject = new ErrorObject(ex.getClass().getSimpleName(), notFound.value(), ex.getMessage(), new Date());

        return new ResponseEntity<>(errorObject, notFound);
    }
}