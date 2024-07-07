package com.ter.proglogic.exceptions;

import java.util.Date;

/**
 * Represents an error object containing details about an exception.
 */
public class ErrorObject {
    private String exceptionName;
    private Integer statusCode;
    private String errorMessage;
    private Date timestamp;

    /**
     * Default constructor.
     */
    public ErrorObject() {
    }

    /**
     * Constructs an ErrorObject with specified details.
     *
     * @param exceptionName the name of the exception
     * @param statusCode the status code associated with the exception
     * @param errorMessage the error message describing the exception
     * @param timestamp the timestamp when the exception occurred
     */
    public ErrorObject(String exceptionName, Integer statusCode, String errorMessage, Date timestamp) {
        this.exceptionName = exceptionName;
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
        this.timestamp = timestamp;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
