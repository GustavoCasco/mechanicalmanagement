package br.com.mechanicalmanagement.mechanicalmanagement.dtos;

import lombok.Builder;
import lombok.Getter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
public class ScheduleDTO {

    private LocalDate dateSchedule;
    private long id_User;
    private long id_Service;
    private LocalTime schedule;
}
