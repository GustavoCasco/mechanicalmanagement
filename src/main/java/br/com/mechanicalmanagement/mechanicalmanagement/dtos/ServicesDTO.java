package br.com.mechanicalmanagement.mechanicalmanagement.dtos;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServicesDTO {
    private long id_service;
    private String service;
    private String descriptionService;
    private LocalTime scheduleEnd;
    @Setter
    private int totalServiceTime;
    private double price;
    private long idUserResponse;
    private List<LocalTime> listAppointmentTimeAvailable = new ArrayList<>();
}
