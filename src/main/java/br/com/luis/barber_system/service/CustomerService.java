package br.com.luis.barber_system.service;


import br.com.luis.barber_system.dto.customer.CustomerLoginDTO;
import br.com.luis.barber_system.dto.customer.CustomerRegisterDTO;
import br.com.luis.barber_system.dto.customer.CustomerResponseDTO;
import br.com.luis.barber_system.dto.customer.CustomerUpdateDTO;
import br.com.luis.barber_system.infra.security.TokenService;
import br.com.luis.barber_system.model.Customer;
import br.com.luis.barber_system.model.enums.UserType;
import br.com.luis.barber_system.repository.BarberRepository;
import br.com.luis.barber_system.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final BarberRepository barberRepository;
    private final AuthenticationManager manager;
    private final TokenService tokenService;
    private final BCryptPasswordEncoder passwordEncoder;

    public CustomerResponseDTO register(CustomerRegisterDTO customer) {

        if (this.repository.findByEmail(customer.email()).isPresent() || this.barberRepository.findByEmail(customer.email()).isPresent()) {
            throw new RuntimeException("Este email já está cadastrado");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(customer.password());

        Customer newCustomer = new Customer(customer);

        newCustomer.setPassword(encryptedPassword);
        newCustomer.setUserType(UserType.CLIENTE);

        Customer savedCustomer = repository.save(newCustomer);

        return customerResponseDTO(savedCustomer);
    }

    public String login(CustomerLoginDTO customerLogin) {

        Customer customer =  repository.findByEmail(customerLogin.email())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        if (!this.passwordEncoder.matches(customerLogin.password(), customer.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        var token = new UsernamePasswordAuthenticationToken(customerLogin.email(), customerLogin.password());
        var auth = manager.authenticate(token);

        return tokenService.generateToken((Customer) auth.getPrincipal());
    }

    public CustomerResponseDTO findById(UUID id) {

        Customer customer = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id não encontrado"));

        return customerResponseDTO(customer);
    }

    public CustomerResponseDTO update(UUID id, CustomerUpdateDTO customerUpdate) {

        Customer customer = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id não encontrado"));

        customer.setName(customerUpdate.name());
        customer.setPhoneNumber(customerUpdate.phoneNumber());

        Customer updatedCustomer = repository.save(customer);

        return customerResponseDTO(updatedCustomer);
    }

    public CustomerResponseDTO profile() {

        Customer customer = repository.findUserByEmail(getAuthenticatedUser());

        return customerResponseDTO(customer);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }


    private CustomerResponseDTO customerResponseDTO(Customer customer) {

        return new CustomerResponseDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getCreatedAt(),
                customer.getUserType()
        );
    }

    private String getAuthenticatedUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
    }
}
