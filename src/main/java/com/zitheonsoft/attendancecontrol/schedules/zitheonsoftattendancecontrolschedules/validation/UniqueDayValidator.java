package com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.validation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.entities.DaySchedule;
import com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.enums.Day;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueDayValidator implements ConstraintValidator<UniqueDay, List<DaySchedule>> {

    @Override
    public boolean isValid(List<DaySchedule> daySchedules, ConstraintValidatorContext context) {
        Set<Day> uniqueDays = new HashSet<>();
        for (DaySchedule daySchedule : daySchedules) {
            if (!uniqueDays.add(daySchedule.getDay())) {
                return false;
            }
        }
        return true; // Todos los días son únicos en el horario
    }
}
