package br.com.mechanicalmanagement.mechanicalmanagement.ports.controller;

import br.com.mechanicalmanagement.mechanicalmanagement.dtos.ScheduleDTO;
import br.com.mechanicalmanagement.mechanicalmanagement.usecase.impls.ScheduleUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleUC scheduleImpl;

    //TODO: FAZER VALIDAÇÃO PARA CASO JÁ EXISTA O AGENDAMENTO NAQUELA HORA
    @PostMapping("/saveSchedule")
    private ResponseEntity<String> saveSchedule(@RequestBody ScheduleDTO scheduleDTO){
        scheduleImpl.saveSchedule(scheduleDTO);
        return ok("Sucesso");
    }
}
