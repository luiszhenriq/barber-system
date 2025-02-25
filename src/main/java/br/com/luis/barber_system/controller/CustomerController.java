package br.com.luis.barber_system.controller;


import br.com.luis.barber_system.dto.customer.CustomerResponseDTO;
import br.com.luis.barber_system.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @GetMapping("/perfil")
    public ResponseEntity<CustomerResponseDTO> perfil() {
        return new ResponseEntity<>(service.profile(), HttpStatus.OK);
    }
}
