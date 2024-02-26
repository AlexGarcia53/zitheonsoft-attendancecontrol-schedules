package com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.utils;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class WrapperResponse<T> {
    private String message;
    private T body;
    private Map<String, String> errors;

    public WrapperResponse(String message, T body) {
        this.message = message;
        this.body = body;
    }

    public WrapperResponse(String message, Map<String, String> errors) {
        this.message = message;
        this.errors = errors;
    }

    public WrapperResponse() {
    }

    public ResponseEntity<WrapperResponse<T>> createResponse(HttpStatus status) {
        return new ResponseEntity<>(this, status);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

}
