package br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tbhg_agendamentos")
public class SchedulingServicesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agendamentos")
    private long idScheduling;
    @Column(name = "dataagendamento")
    private LocalDate dateSchedule;
    @Column(name = "id_usuario")
    private long id_User;
    @Column(name = "id_servicos")
    private long id_Services;
    @Column(name = "id_status")
    private long id_status;
    @ManyToOne
    @JoinColumn(name = "id_horario")
    private AppointmentTimesEntity timetable;
}
