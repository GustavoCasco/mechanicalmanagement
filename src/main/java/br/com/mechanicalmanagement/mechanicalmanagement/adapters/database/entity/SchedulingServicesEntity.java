package br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Table
@Builder
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

    @OneToOne
    @JoinColumn(name = "id_horario")
    private AppointmentTimesEntity timetable;
}
