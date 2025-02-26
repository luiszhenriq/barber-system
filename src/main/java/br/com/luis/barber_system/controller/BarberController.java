package br.com.luis.barber_system.controller;


import br.com.luis.barber_system.dto.barber.BarberResponseDTO;
import br.com.luis.barber_system.service.BarberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/barber")
@RequiredArgsConstructor
public class BarberController {

    private final BarberService service;


    @GetMapping
    public ResponseEntity<List<BarberResponseDTO>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
}
