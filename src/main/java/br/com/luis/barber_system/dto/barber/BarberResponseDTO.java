package br.com.luis.barber_system.dto.barber;

import br.com.luis.barber_system.model.enums.UserType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record BarberResponseDTO(
        UUID id,

        String name,

        String email,

        String phoneNumber,

        LocalDateTime createdAt,

        List<LocalDateTime> availableHours,

        UserType userType

) {
}
