package br.com.luis.barber_system.repository;

import br.com.luis.barber_system.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    Optional<Customer> findByEmail(String email);

    Customer findUserByEmail(String email);
}
