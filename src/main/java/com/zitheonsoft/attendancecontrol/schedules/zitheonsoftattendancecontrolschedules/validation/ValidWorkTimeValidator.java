package com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.validation;

import java.time.LocalTime;

import com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.entities.DaySchedule;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidWorkTimeValidator implements ConstraintValidator<ValidWorkTime, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        if (value instanceof DaySchedule) {

            DaySchedule daySchedule = (DaySchedule) value;
            LocalTime startWorkTime = daySchedule.getStartWorkTime();
            LocalTime endWorkTime = daySchedule.getEndWorkTime();
            LocalTime startLunchTime = daySchedule.getStartLunchTime();
            LocalTime endLunchTime = daySchedule.getEndLunchTime();

            return startWorkTime.isBefore(endWorkTime) && startLunchTime.isBefore(endLunchTime)
                    && startLunchTime.isAfter(startWorkTime) && endLunchTime.isBefore(endWorkTime);
        }
        return true;
    }

}
