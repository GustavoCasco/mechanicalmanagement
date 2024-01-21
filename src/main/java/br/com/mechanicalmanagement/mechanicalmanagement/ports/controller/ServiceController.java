package br.com.mechanicalmanagement.mechanicalmanagement.ports.controller;

import br.com.mechanicalmanagement.mechanicalmanagement.dtos.ServicesDTO;
import br.com.mechanicalmanagement.mechanicalmanagement.usecase.impls.ServicesList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "/test")
@RequiredArgsConstructor
public class ServiceController {

    private final ServicesList servicesList;

    @PostMapping("/save")
    private ResponseEntity<Object> saveService(@RequestBody ServicesDTO servicesDTO){
        servicesList.saveServices(servicesDTO);
        return ResponseEntity.ok("Sucesso");
    }
}
