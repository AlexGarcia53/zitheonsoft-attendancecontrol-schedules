package com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.services;

import java.util.List;
import java.util.Optional;

import com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.entities.Schedule;

public interface IScheduleService {
    Optional<Schedule> findById(Long id);

    Schedule create(Schedule schedule);

    Schedule update(Long id, Schedule schedule);

    Schedule delete(Long id);

    List<Schedule> findAll(int pageNumber, int pageSize);

    List<Schedule> findByName(String name);

    boolean existsByName(String username);

}
