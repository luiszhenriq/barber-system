package br.com.luis.barber_system.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "barbers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Barber extends Person{

    private List<LocalDateTime> availableHours;
}
