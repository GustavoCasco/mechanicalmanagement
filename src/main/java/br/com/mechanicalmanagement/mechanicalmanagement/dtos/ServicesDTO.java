package br.com.mechanicalmanagement.mechanicalmanagement.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ServicesDTO {

    private String service;
    private String descriptionService;
    private LocalTime scheduleEnd;
    private int totalServiceTime;
    private double price;
    private long idUserResponse;
}
