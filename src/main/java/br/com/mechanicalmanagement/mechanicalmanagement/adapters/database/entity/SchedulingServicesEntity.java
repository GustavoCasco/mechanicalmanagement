package br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

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
    private long id_Scheduling;

    @Column(name = "dataagendamento")
    private Date dateSchedule;

    @Column(name = "id_usuario")
    private long id_User;

    @Column(name = "id_servicos")
    private long id_Services;

    @OneToOne
    @JoinColumn(name = "id_horario")
    private AppointmentTimesEntity timetable;
}
