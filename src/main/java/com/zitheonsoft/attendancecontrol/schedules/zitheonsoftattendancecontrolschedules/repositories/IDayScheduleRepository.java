package com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.entities.DaySchedule;

public interface IDayScheduleRepository extends JpaRepository<DaySchedule, Long> {

}
