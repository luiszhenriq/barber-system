package br.com.luis.barber_system.dto.barber;

import java.time.LocalDateTime;
import java.util.List;

public record BarberRegisterDTO(

        String name,

        String email,

        String phoneNumber,

        String password,

        List<LocalDateTime> availableHours
) {
}
