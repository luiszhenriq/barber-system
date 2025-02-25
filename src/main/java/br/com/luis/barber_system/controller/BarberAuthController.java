package br.com.luis.barber_system.controller;


import br.com.luis.barber_system.dto.barber.BarberLoginDTO;
import br.com.luis.barber_system.dto.barber.BarberRegisterDTO;
import br.com.luis.barber_system.dto.barber.BarberResponseDTO;
import br.com.luis.barber_system.infra.security.TokenJWT;
import br.com.luis.barber_system.service.BarberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/barber/auth")
@RequiredArgsConstructor
public class BarberAuthController {

    private final BarberService service;

    @PostMapping("/register")
    public ResponseEntity<BarberResponseDTO> register(@RequestBody BarberRegisterDTO register) {
        return new ResponseEntity<>(service.register(register), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenJWT> login(@RequestBody BarberLoginDTO login) {
        String tokenJWT = service.login(login);
        return ResponseEntity.ok(new TokenJWT(tokenJWT));
    }
}
