package br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tbhg_agendamentos")
public class SchedulingServicesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_preco")
    private long id_Scheduling;
    @Column(name = "id_usuario")
    private long id_User;
    @Column(name = "id_servicos")
    private long id_Services;
    @Column(name = "datahorarioinicio")
    private long dateHoursStart;
    @Column(name = "datahorariofim")
    private long dateHoursEnd;
}
