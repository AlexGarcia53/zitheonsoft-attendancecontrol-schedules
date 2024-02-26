package com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.exceptions;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.entities.Schedule;
import com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.utils.WrapperResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<WrapperResponse<Schedule>> handleConstraintViolationException(
            ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            String field = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errors.put(field, "Error en: " + field + " -> " + message);
        });
        WrapperResponse<Schedule> response = new WrapperResponse<>("Error de validación", errors);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<WrapperResponse<Schedule>> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Integridad de datos", "Error de integridad de datos: " + ex.getMessage());
        WrapperResponse<Schedule> response = new WrapperResponse<>("Error de integridad de datos", errors);
        return ResponseEntity.badRequest().body(response);
    }

}