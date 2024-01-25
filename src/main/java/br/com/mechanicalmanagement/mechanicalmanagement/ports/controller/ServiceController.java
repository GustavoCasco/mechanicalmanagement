package br.com.mechanicalmanagement.mechanicalmanagement.ports.controller;

import br.com.mechanicalmanagement.mechanicalmanagement.dtos.ServicesDTO;
import br.com.mechanicalmanagement.mechanicalmanagement.usecase.impls.ScheduleImpl;
import br.com.mechanicalmanagement.mechanicalmanagement.usecase.impls.ServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/service")
@RequiredArgsConstructor
public class ServiceController {

    private final ServicesImpl serviceImpl;
    private final ScheduleImpl scheduleService;

    @PostMapping("/save")
    private ResponseEntity<Object> saveService(@RequestBody ServicesDTO servicesDTO){
        serviceImpl.saveServices(servicesDTO);
        scheduleService.saveSchedule(servicesDTO.getService(), servicesDTO.getScheduleEnd(), servicesDTO.getTotalServiceTime());
        return ResponseEntity.ok("Sucesso");
    }

    @GetMapping("/listall")
    private ResponseEntity<List<ServicesDTO>> listAllServices(){
        return ResponseEntity.ok(serviceImpl.listAllServices());
    }
}
