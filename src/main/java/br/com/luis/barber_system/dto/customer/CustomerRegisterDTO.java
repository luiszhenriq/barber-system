package br.com.luis.barber_system.dto.customer;

public record CustomerRegisterDTO(
        String name,

        String email,

        String phoneNumber,

        String password
) {
}
