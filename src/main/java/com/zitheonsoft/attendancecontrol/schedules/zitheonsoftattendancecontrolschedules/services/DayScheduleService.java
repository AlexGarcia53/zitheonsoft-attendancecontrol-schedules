package com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.entities.DaySchedule;
import com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.repositories.IDayScheduleRepository;

@Service
public class DayScheduleService implements IDayScheduleService {

    @Autowired
    private IDayScheduleRepository dayScheduleRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<DaySchedule> findById(Long id) {
        return dayScheduleRepository.findById(id);
    }

}
