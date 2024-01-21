package br.com.mechanicalmanagement.mechanicalmanagement.dtos;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ServicesDTO {

    private String service;
    private String descriptionService;
    private double price;
}
