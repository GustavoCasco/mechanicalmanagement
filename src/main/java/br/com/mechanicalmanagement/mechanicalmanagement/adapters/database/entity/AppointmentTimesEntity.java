package br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

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
    private long idSchedule;
    @Column(name = "horario")
    private LocalTime schedule;
    @Column(name = "tempoatendimento")
    private int serviceTime;
    @ManyToOne
    @JoinColumn(name = "id_servicos")
    private ServicesEntity servicesEntity;
    @OneToMany(mappedBy = "timetable")
    private List<SchedulingServicesEntity> schedulingServicesEntity;
}
