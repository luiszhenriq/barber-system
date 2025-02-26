package br.com.luis.barber_system.service;


import br.com.luis.barber_system.dto.barber.BarberLoginDTO;
import br.com.luis.barber_system.dto.barber.BarberRegisterDTO;
import br.com.luis.barber_system.dto.barber.BarberResponseDTO;
import br.com.luis.barber_system.infra.security.TokenService;
import br.com.luis.barber_system.model.Barber;
import br.com.luis.barber_system.model.enums.UserType;
import br.com.luis.barber_system.repository.BarberRepository;
import br.com.luis.barber_system.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BarberService {

    private final BarberRepository repository;
    private final CustomerRepository customerRepository;
    private final AuthenticationManager manager;
    private final TokenService tokenService;
    private final BCryptPasswordEncoder passwordEncoder;


    public BarberResponseDTO register(BarberRegisterDTO barber) {

        if (this.repository.findByEmail(barber.email()).isPresent() || this.customerRepository.findByEmail(barber.email()).isPresent()) {
            throw new RuntimeException("Este email já está cadastrado");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(barber.password());

        Barber newBarber = new Barber(barber);

        newBarber.setPassword(encryptedPassword);
        newBarber.setUserType(UserType.BARBEIRO);

        Barber savedBarber = repository.save(newBarber);

        return barberResponseDTO(savedBarber);
    }

    public String login(BarberLoginDTO barberLoginDTO) {

        Barber barber =  repository.findByEmail(barberLoginDTO.email())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        if (!this.passwordEncoder.matches(barberLoginDTO.password(), barber.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        var token = new UsernamePasswordAuthenticationToken(barberLoginDTO.email(), barberLoginDTO.password());
        var auth = manager.authenticate(token);

        return tokenService.generateToken((Barber) auth.getPrincipal());
    }

    public List<BarberResponseDTO> findAll() {

        return repository.findAll()
                .stream()
                .map(this::barberResponseDTO)
                .collect(Collectors.toList());
    }

    private BarberResponseDTO barberResponseDTO(Barber barber) {

        return new BarberResponseDTO(
                barber.getId(),
                barber.getName(),
                barber.getEmail(),
                barber.getPhoneNumber(),
                barber.getCreatedAt(),
                barber.getAvailableHours(),
                barber.getUserType()
        );
    }
}
