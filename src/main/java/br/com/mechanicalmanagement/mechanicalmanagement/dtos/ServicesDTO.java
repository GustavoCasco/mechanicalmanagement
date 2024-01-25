package br.com.mechanicalmanagement.mechanicalmanagement.dtos;


import lombok.*;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ServicesDTO {

    private String service;
    private String descriptionService;
    private LocalTime scheduleEnd;
    private LocalTime totalServiceTime;
    private double price;
}
