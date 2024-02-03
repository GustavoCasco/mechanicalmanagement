package br.com.mechanicalmanagement.mechanicalmanagement.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.time.LocalTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ScheduleDTO(
        LocalDate dateSchedule,
        long id_User,
        long id_Service,
        long id_status,
        LocalTime schedule) {
}
