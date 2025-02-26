package br.com.luis.barber_system.controller;


import br.com.luis.barber_system.dto.barber.BarberResponseDTO;
import br.com.luis.barber_system.dto.barber.BarberUpdateDTO;
import br.com.luis.barber_system.service.BarberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/barber")
@RequiredArgsConstructor
public class BarberController {

    private final BarberService service;


    @GetMapping
    public ResponseEntity<List<BarberResponseDTO>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BarberResponseDTO> findById(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BarberResponseDTO> update(@PathVariable("id") UUID id,
                                                    @RequestBody BarberUpdateDTO barberUpdate) {
        return new ResponseEntity<>(service.update(id, barberUpdate), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") UUID id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
