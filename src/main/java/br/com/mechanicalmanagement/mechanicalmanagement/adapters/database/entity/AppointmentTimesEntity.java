package br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Table
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tbhg_horarios")
public class AppointmentTimesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_horario")
    private long id_Schedule;
    @Column(name = "horario")
    private LocalTime schedule;
    @ManyToOne
    @JoinColumn(name = "id_servicos")
    private ServicesEntity servicesEntity;
}
