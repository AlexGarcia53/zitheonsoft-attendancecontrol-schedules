package com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidWorkTimeValidator.class)
public @interface ValidWorkTime {
    String message() default "La hora de inicio no puede ser mayor que la hora de fin!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}