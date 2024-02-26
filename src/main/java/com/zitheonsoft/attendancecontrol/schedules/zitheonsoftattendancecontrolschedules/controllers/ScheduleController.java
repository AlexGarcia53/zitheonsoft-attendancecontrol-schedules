package com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.entities.Schedule;
import com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.services.IScheduleService;
import com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.utils.ResquestValidation;
import com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.utils.WrapperResponse;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired
    private IScheduleService scheduleService;

    @Autowired
    private ResquestValidation resquestValidation;

    @PostMapping
    public ResponseEntity<WrapperResponse<Schedule>> create(@Valid @RequestBody Schedule schedule,
            BindingResult result) {
        Schedule createdSchedule = scheduleService.create(schedule);
        WrapperResponse<Schedule> response = new WrapperResponse<>("Horario creado exitosamente", createdSchedule);
        return response.createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WrapperResponse<Schedule>> update(@Valid @RequestBody Schedule schedule,
            @PathVariable Long id, BindingResult result) {

        Schedule updatedSchedule = scheduleService.update(id, schedule);

        WrapperResponse<Schedule> response = new WrapperResponse<>("Horario actualizado exitosamente", updatedSchedule);
        return response.createResponse(HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WrapperResponse<Schedule>> delete(@PathVariable Long id) {
        Schedule deletedSchedule = scheduleService.delete(id);
        WrapperResponse<Schedule> response = new WrapperResponse<>("Horario eliminado exitosamente", deletedSchedule);
        return response.createResponse(HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<WrapperResponse<List<Schedule>>> findAll(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        List<Schedule> schedules = scheduleService.findAll(pageable.getPageNumber(), pageable.getPageSize());
        WrapperResponse<List<Schedule>> response = new WrapperResponse<>("Horarios obtenidos", schedules);
        return response.createResponse(HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<WrapperResponse<List<Schedule>>> findByName(@RequestParam String param) {
        List<Schedule> schedules = scheduleService.findByName(param);
        WrapperResponse<List<Schedule>> response = new WrapperResponse<>("Horarios obtenidos", schedules);
        return response.createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WrapperResponse<Schedule>> findById(@PathVariable Long id) {
        Optional<Schedule> scheduleOptional = scheduleService.findById(id);

        if (scheduleOptional.isPresent()) {
            WrapperResponse<Schedule> response = new WrapperResponse<>("Horario obtenido", scheduleOptional.get());
            return response.createResponse(HttpStatus.OK);
        } else {
            WrapperResponse<Schedule> response = new WrapperResponse<>(
                    "No se encontró ningún horario con el ID proporcionado", null);
            return response.createResponse(HttpStatus.NOT_FOUND);
        }
    }

}
