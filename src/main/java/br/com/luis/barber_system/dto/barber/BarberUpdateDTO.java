package br.com.luis.barber_system.dto.barber;

import java.time.LocalDateTime;
import java.util.List;

public record BarberUpdateDTO(

        String name,

        String phoneNumber,

        List<LocalDateTime> availableHours
) {
}
