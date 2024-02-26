package com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.entities;

import java.time.LocalTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.enums.Day;
import com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.enums.Mode;
import com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.enums.Type;
import com.zitheonsoft.attendancecontrol.schedules.zitheonsoftattendancecontrolschedules.validation.ValidWorkTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "day_schedules")
@ValidWorkTime
public class DaySchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    @JsonBackReference
    private Schedule schedule;

    @Column(name = "start_work_time")
    @Temporal(TemporalType.TIME)
    private LocalTime startWorkTime;

    @Column(name = "end_work_time")
    @Temporal(TemporalType.TIME)
    private LocalTime endWorkTime;

    @Column(name = "start_lunch_time")
    @Temporal(TemporalType.TIME)
    private LocalTime startLunchTime;

    @Column(name = "end_lunch_time")
    @Temporal(TemporalType.TIME)
    private LocalTime endLunchTime;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "day")
    private Day day;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "Mode")
    private Mode mode;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "Type")
    private Type type;

    public DaySchedule() {

    }

    public DaySchedule(LocalTime startWorkTime, LocalTime endWorkTime,
            LocalTime startLunchTime, LocalTime endLunchTime, Day day, Mode mode, Type type) {
        this.startWorkTime = startWorkTime;
        this.endWorkTime = endWorkTime;
        this.startLunchTime = startLunchTime;
        this.endLunchTime = endLunchTime;
        this.day = day;
        this.mode = mode;
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DaySchedule other = (DaySchedule) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DaySchedule [id=" + id + ", schedule=" + schedule + ", startWorkTime=" + startWorkTime
                + ", endWorkTime=" + endWorkTime + ", startLunchTime=" + startLunchTime + ", endLunchTime="
                + endLunchTime + ", day=" + day + ", mode=" + mode + ", type=" + type + "]";
    }

}
