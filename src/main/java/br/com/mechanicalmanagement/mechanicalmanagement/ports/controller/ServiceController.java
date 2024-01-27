package br.com.mechanicalmanagement.mechanicalmanagement.ports.controller;

import br.com.mechanicalmanagement.mechanicalmanagement.dtos.ServicesDTO;
import br.com.mechanicalmanagement.mechanicalmanagement.usecase.impls.AppointmentTimesUC;
import br.com.mechanicalmanagement.mechanicalmanagement.usecase.impls.ServicesUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/service")
@RequiredArgsConstructor
public class ServiceController {

    private final ServicesUC serviceImpl;
    private final AppointmentTimesUC scheduleService;

    @PostMapping("/save")
    private ResponseEntity<Object> saveService(@RequestBody ServicesDTO servicesDTO){
        serviceImpl.saveServices(servicesDTO);
        scheduleService.saveAppointmentTimes(servicesDTO.getService(), servicesDTO.getScheduleEnd(), servicesDTO.getTotalServiceTime());
        return ResponseEntity.ok("Sucesso");
    }

    @GetMapping("/listall")
    private ResponseEntity<List<ServicesDTO>> listAllServices(){
        return ResponseEntity.ok(serviceImpl.listAllServices());
    }
}
