package br.com.mechanicalmanagement.mechanicalmanagement.ports.controller;

import br.com.mechanicalmanagement.mechanicalmanagement.dtos.ScheduleDTO;
import br.com.mechanicalmanagement.mechanicalmanagement.usecase.impls.ScheduleUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleUC scheduleImpl;

    @PostMapping("/saveSchedule")
    private ResponseEntity<String> saveSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        scheduleImpl.saveSchedule(scheduleDTO);
        return ok("Sucesso");
    }

    @PatchMapping("/updateStatusSchedule")
    private ResponseEntity<String> updateStatusSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        scheduleImpl.updateStatus(scheduleDTO);
        return ok("Sucesso");
    }

    @GetMapping("/listAppointmentTimeForService")
    private ResponseEntity<Set<LocalTime>> listAllAppointmentTimes(@RequestParam String serviceName,
                                                                   @RequestParam LocalDate dateSchedule){
        var listAllScheduleForService = scheduleImpl.findAllScheduleAvailable(serviceName, dateSchedule);
        return ok(listAllScheduleForService);
    }
}
