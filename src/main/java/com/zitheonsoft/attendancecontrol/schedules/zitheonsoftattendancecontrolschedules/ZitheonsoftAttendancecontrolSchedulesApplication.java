package com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.entities.DaySchedule;
import com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.entities.Schedule;
import com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.enums.Day;
import com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.enums.Mode;
import com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.enums.Type;
import com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.repositories.IScheduleRepository;

@SpringBootApplication
public class ZitheonsoftAttendancecontrolSchedulesApplication implements CommandLineRunner {

	@Autowired
	private IScheduleRepository scheduleRepository;

	public static void main(String[] args) {
		SpringApplication.run(ZitheonsoftAttendancecontrolSchedulesApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		// loadTestSchedule();
	}

	public void loadTestSchedule() {
		Schedule schedule = new Schedule();

		List<DaySchedule> daySchedules = new ArrayList<>();

		// Lunes
		DaySchedule mondaySchedule = new DaySchedule(LocalTime.parse("09:00:00"), LocalTime.parse("13:00:00"),
				LocalTime.parse("11:30:00"), LocalTime.parse("12:00:00"), Day.MONDAY, Mode.REMOTE, Type.FIXED);
		mondaySchedule.setSchedule(schedule);
		daySchedules.add(mondaySchedule);

		// Martes
		DaySchedule tuesdaySchedule = new DaySchedule(LocalTime.parse("07:00:00"), LocalTime.parse("12:00:00"),
				LocalTime.parse("10:30:00"), LocalTime.parse("11:00:00"), Day.TUESDAY, Mode.REMOTE, Type.FIXED);
		tuesdaySchedule.setSchedule(schedule);
		daySchedules.add(tuesdaySchedule);

		// Miércoles
		DaySchedule wednesdaySchedule = new DaySchedule(LocalTime.parse("09:00:00"), LocalTime.parse("13:00:00"),
				LocalTime.parse("11:30:00"), LocalTime.parse("12:00:00"), Day.WEDNESDAY, Mode.REMOTE, Type.FIXED);
		wednesdaySchedule.setSchedule(schedule);
		daySchedules.add(wednesdaySchedule);

		// Jueves
		DaySchedule thursdaySchedule = new DaySchedule(LocalTime.parse("08:00:00"), LocalTime.parse("14:00:00"),
				LocalTime.parse("12:00:00"), LocalTime.parse("12:30:00"), Day.THURSDAY, Mode.REMOTE, Type.FIXED);
		thursdaySchedule.setSchedule(schedule);
		daySchedules.add(thursdaySchedule);

		// Viernes
		DaySchedule fridaySchedule = new DaySchedule(LocalTime.parse("08:30:00"), LocalTime.parse("12:30:00"),
				LocalTime.parse("11:00:00"), LocalTime.parse("12:00:00"), Day.FRIDAY, Mode.REMOTE, Type.FIXED);
		fridaySchedule.setSchedule(schedule);
		daySchedules.add(fridaySchedule);

		schedule.setDaySchedules(daySchedules);

		scheduleRepository.save(schedule);
	}

}
