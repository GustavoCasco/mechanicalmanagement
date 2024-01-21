package br.com.mechanicalmanagement.mechanicalmanagement.ports.controller;

import br.com.mechanicalmanagement.mechanicalmanagement.dtos.UserDTO;
import br.com.mechanicalmanagement.mechanicalmanagement.usecase.impls.UserManagementImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserManagementImpl userManagement;
    @PostMapping("/save")
    private ResponseEntity<Object> saveUser(@RequestBody UserDTO servicesDTO){
        userManagement.addingNewUser(servicesDTO);
        return ResponseEntity.ok("Sucesso");
    }
}