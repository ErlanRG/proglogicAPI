package com.ter.proglogic.exceptions;

import java.util.Date;

public class ErrorObject {
    private Integer statusCode;
    private String errorMessage;
    private Date timestamp;

    public ErrorObject() {
    }

    public ErrorObject(Integer statusCode, String errorMessage, Date timestamp) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
        this.timestamp = timestamp;
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
