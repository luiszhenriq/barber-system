package br.com.luis.barber_system.controller;


import br.com.luis.barber_system.dto.customer.CustomerLoginDTO;
import br.com.luis.barber_system.dto.customer.CustomerRegisterDTO;
import br.com.luis.barber_system.dto.customer.CustomerResponseDTO;
import br.com.luis.barber_system.infra.security.TokenJWT;
import br.com.luis.barber_system.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer/auth")
@RequiredArgsConstructor
public class CustomerAuthController {

    private final CustomerService service;

    @PostMapping("/register")
    public ResponseEntity<CustomerResponseDTO> register(@RequestBody CustomerRegisterDTO register) {
        return new ResponseEntity<>(service.register(register), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenJWT> login(@RequestBody CustomerLoginDTO login) {
        String tokenJWT = service.login(login);
        return ResponseEntity.ok(new TokenJWT(tokenJWT));
    }
}
