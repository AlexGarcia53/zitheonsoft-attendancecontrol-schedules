package com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.entities.Schedule;

@Component
public class ResquestValidation {
    public ResponseEntity<WrapperResponse<Schedule>> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "Error en: " + err.getField() + " -> " + err.getDefaultMessage());
        });
        WrapperResponse<Schedule> response = new WrapperResponse<>("Error de validación", errors);
        return ResponseEntity.badRequest().body(response);
    }
}
