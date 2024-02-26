package com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.services;

import java.util.Optional;

import com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.entities.DaySchedule;

public interface IDayScheduleService {
        Optional<DaySchedule> findById(Long id);
}
