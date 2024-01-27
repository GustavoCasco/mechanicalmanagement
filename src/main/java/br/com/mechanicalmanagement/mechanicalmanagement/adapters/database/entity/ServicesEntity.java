package br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Table
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tbhg_servicos")
public class ServicesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicos")
    private Long idServices;
    @Column(name = "servico")
    private String service;
    @Column(name = "descricaoservico")
    private String descriptionService;
    @ManyToOne
    @JoinColumn(name = "id_preco")
    private PriceEntity priceEntity;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UserEntity id_User;

    @OneToMany(mappedBy = "servicesEntity")
    private Set<AppointmentTimesEntity> appointmentTimes;
}
