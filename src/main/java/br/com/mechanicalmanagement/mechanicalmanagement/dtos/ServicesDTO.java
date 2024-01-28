package br.com.mechanicalmanagement.mechanicalmanagement.dtos;


import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ServicesDTO (
     long id_service,
     String service,
     String descriptionService,
     LocalTime scheduleEnd,
     int totalServiceTime,
     double price,
     long idUserResponse,
     TreeSet<LocalTime> listAppointmentTimeAvailable
    ){
}
