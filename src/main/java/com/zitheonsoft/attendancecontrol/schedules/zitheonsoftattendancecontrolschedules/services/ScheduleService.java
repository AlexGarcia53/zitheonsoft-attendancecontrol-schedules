package com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.entities.DaySchedule;
import com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.entities.Schedule;
import com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.exceptions.NoDataFoundException;
import com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.repositories.IDayScheduleRepository;
import com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.repositories.IScheduleRepository;

@Service
public class ScheduleService implements IScheduleService {

    @Autowired
    private IScheduleRepository scheduleRepository;

    @Autowired
    private IDayScheduleRepository dayScheduleRepository;

    @Override
    public Schedule create(Schedule schedule) {
        Schedule savedSchedule = scheduleRepository.save(schedule);

        for (DaySchedule daySchedule : schedule.getDaySchedules()) {
            daySchedule.setSchedule(savedSchedule);
        }

        List<DaySchedule> savedDaySchedules = dayScheduleRepository.saveAll(schedule.getDaySchedules());

        savedSchedule.setDaySchedules(savedDaySchedules);

        return savedSchedule;
    }

    @Override
    public Schedule delete(Long id) {
        Schedule scheduleObtained = scheduleRepository.findById(id)
                .orElseThrow(() -> new NoDataFoundException("No se encontró el horario"));
        scheduleRepository.delete(scheduleObtained);
        return scheduleObtained;
    }

    @Transactional
    @Override
    public List<Schedule> findAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Schedule> schedulePage = scheduleRepository.findAll(pageable);
        return schedulePage.getContent();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Schedule> findById(Long id) {
        return scheduleRepository.findById(id);
    }

    @Override
    public List<Schedule> findByName(String name) {
        return scheduleRepository.findByName(name);
    }

    @Override
    public Schedule update(Long id, Schedule schedule) {
        Optional<Schedule> existingScheduleOptional = scheduleRepository.findById(id);

        if (existingScheduleOptional.isPresent()) {
            Schedule existingSchedule = existingScheduleOptional.get();

            existingSchedule.setName(schedule.getName());

            List<DaySchedule> updatedDaySchedules = new ArrayList<>();
            for (DaySchedule daySchedule : schedule.getDaySchedules()) {

                Optional<DaySchedule> existingDayScheduleOptional = existingSchedule.getDaySchedules().stream()
                        .filter(ds -> ds.getDay().equals(daySchedule.getDay()))
                        .findFirst();

                if (existingDayScheduleOptional.isPresent()) {

                    DaySchedule existingDaySchedule = existingDayScheduleOptional.get();
                    existingDaySchedule.setStartWorkTime(daySchedule.getStartWorkTime());
                    existingDaySchedule.setEndWorkTime(daySchedule.getEndWorkTime());
                    existingDaySchedule.setStartLunchTime(daySchedule.getStartLunchTime());
                    existingDaySchedule.setEndLunchTime(daySchedule.getEndLunchTime());
                    existingDaySchedule.setMode(daySchedule.getMode());
                    existingDaySchedule.setType(daySchedule.getType());

                    updatedDaySchedules.add(existingDaySchedule);
                } else {
                    throw new NoDataFoundException("No se encontró el día: " + daySchedule.getDay() + "en el horario");
                }
            }

            existingSchedule.setDaySchedules(updatedDaySchedules);

            return scheduleRepository.save(existingSchedule);
        } else {

            throw new NoDataFoundException("No se encontró un horario con el ID: " + id);
        }
    }

    @Override
    public boolean existsByName(String username) {
        return scheduleRepository.existsByName(username);
    }

}
