package br.com.luis.barber_system.controller;


import br.com.luis.barber_system.dto.customer.CustomerResponseDTO;
import br.com.luis.barber_system.dto.customer.CustomerUpdateDTO;
import br.com.luis.barber_system.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @GetMapping("/perfil")
    public ResponseEntity<CustomerResponseDTO> perfil() {
        return new ResponseEntity<>(service.profile(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> findById(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> update(@PathVariable("id") UUID id,
                                                      @RequestBody CustomerUpdateDTO customerUpdate) {
        return new ResponseEntity<>(service.update(id, customerUpdate), HttpStatus.OK);
    }
}
