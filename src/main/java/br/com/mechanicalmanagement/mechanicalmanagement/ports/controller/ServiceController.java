package br.com.mechanicalmanagement.mechanicalmanagement.ports.controller;

import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.ServicesEntity;
import br.com.mechanicalmanagement.mechanicalmanagement.dtos.ServicesDTO;
import br.com.mechanicalmanagement.mechanicalmanagement.usecase.impls.ServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/service")
@RequiredArgsConstructor
public class ServiceController {

    private final ServicesImpl servicesList;

    @PostMapping("/save")
    private ResponseEntity<Object> saveService(@RequestBody ServicesDTO servicesDTO){
        servicesList.saveServices(servicesDTO);
        return ResponseEntity.ok("Sucesso");
    }

    @GetMapping("/listall")
    private ResponseEntity<List<ServicesDTO>> listAllServices(){
        return ResponseEntity.ok(servicesList.listAllServices());
    }
}
