package br.com.mechanicalmanagement.mechanicalmanagement.ports.controller;

import br.com.mechanicalmanagement.mechanicalmanagement.usecase.impls.ScheduleUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleUC scheduleImpl;

    @GetMapping("/listschedulesforservice")
    private ResponseEntity<List<LocalTime>> listAllScheduleForService(@RequestParam String serviceName){
        var listAllScheduleForService = scheduleImpl.findAllScheduleAvailable(serviceName);
        listAllScheduleForService.sort(LocalTime::compareTo);
        return ok(listAllScheduleForService);
    }
}
