package br.com.luis.barber_system.dto.customer;

import br.com.luis.barber_system.model.enums.UserType;

import java.time.LocalDateTime;
import java.util.UUID;

public record CustomerResponseDTO(
        UUID id,

        String name,

        String email,

        String phoneNumber,

        LocalDateTime createdAt,

        UserType userType

) {
}
