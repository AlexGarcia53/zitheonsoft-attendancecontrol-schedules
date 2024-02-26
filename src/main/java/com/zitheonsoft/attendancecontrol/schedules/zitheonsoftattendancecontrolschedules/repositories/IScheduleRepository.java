package com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.entities.Schedule;
import java.util.List;

public interface IScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s FROM Schedule s WHERE s.name LIKE %:name%")
    List<Schedule> findByName(@Param("name") String name);

    boolean existsByName(String username);

}
