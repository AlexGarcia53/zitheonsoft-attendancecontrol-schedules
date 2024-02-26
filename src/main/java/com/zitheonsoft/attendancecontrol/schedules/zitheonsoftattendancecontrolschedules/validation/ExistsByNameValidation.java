package com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.validation;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.services.ScheduleService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ExistsByNameValidation implements ConstraintValidator<ExistsByName, String> {

    @Autowired
    private ScheduleService service;

    private boolean validateOnUpdate;

    @Override
    public void initialize(ExistsByName constraint) {
        validateOnUpdate = constraint.ignoreOnUpdate();
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if (!validateOnUpdate) {
            return true;
        }

        if (service == null) {
            return true;
        }
        return !service.existsByName(name);
    }
}
