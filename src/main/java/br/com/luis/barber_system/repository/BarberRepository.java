package br.com.luis.barber_system.repository;

import br.com.luis.barber_system.model.Barber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BarberRepository extends JpaRepository<Barber, UUID> {

    Optional<Barber> findByEmail(String email);
}
