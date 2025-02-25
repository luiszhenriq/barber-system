package br.com.luis.barber_system.service;


import br.com.luis.barber_system.repository.BarberRepository;
import br.com.luis.barber_system.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final BarberRepository barberRepository;
    private final CustomerRepository customerRepository;


    public Optional<? extends UserDetails> findByEmail(String email) {
        return customerRepository.findByEmail(email)
                .map(user -> (UserDetails) user)
                .or(() -> barberRepository.findByEmail(email));
    }
}
