package com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ExistsByNameValidationException extends RuntimeException {

    public ExistsByNameValidationException(String message) {
        super(message);
    }
}
