package br.com.luis.barber_system.model;


import br.com.luis.barber_system.dto.barber.BarberRegisterDTO;
import br.com.luis.barber_system.dto.customer.CustomerRegisterDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "barbers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Barber extends User {

    private List<LocalDateTime> availableHours;

    public Barber(BarberRegisterDTO barber) {
        this.setName(barber.name());
        this.setEmail(barber.email());
        this.setPhoneNumber(barber.phoneNumber());
        this.setPassword(barber.password());
        this.setAvailableHours(barber.availableHours());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public String getUsername() {
        return this.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
