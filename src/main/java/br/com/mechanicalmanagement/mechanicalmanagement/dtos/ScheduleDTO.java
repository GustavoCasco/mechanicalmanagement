package br.com.mechanicalmanagement.mechanicalmanagement.dtos;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;


public record ScheduleDTO(LocalDate dateSchedule,
                          long id_User,
                          long id_Service,
                          LocalTime schedule) {
}
