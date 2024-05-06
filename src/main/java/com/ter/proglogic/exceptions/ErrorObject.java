package com.ter.proglogic.exceptions;

import java.util.Date;

public class ErrorObject {
    private String exceptionName;
    private Integer statusCode;
    private String errorMessage;
    private Date timestamp;

    public ErrorObject() {
    }

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
