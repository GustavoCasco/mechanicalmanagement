package br.com.mechanicalmanagement.mechanicalmanagement.ports.controller;

import br.com.mechanicalmanagement.mechanicalmanagement.usecase.impls.AppointmentTimesUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/appointmentTimes")
@RequiredArgsConstructor
public class ScheduleController {

    private final AppointmentTimesUC scheduleImpl;

    @GetMapping("/listAppointmentTimeForService")
    private ResponseEntity<List<LocalTime>> listAllAppointmentTimes(@RequestParam String serviceName,
                                                                    @RequestParam Date dateSchedule){
        var listAllScheduleForService = scheduleImpl.findAllScheduleAvailable(serviceName, dateSchedule);
        listAllScheduleForService.sort(LocalTime::compareTo);
        return ok(listAllScheduleForService);
    }
}
