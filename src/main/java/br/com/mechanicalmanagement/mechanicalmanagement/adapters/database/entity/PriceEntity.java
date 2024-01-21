package br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tbhg_preco")
public class PriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_preco")
    private long id_Price;
    @Column(name = "preco")
    private double price;

    @OneToMany(mappedBy="priceEntity")
    private List<ServicesEntity> servicesEntity;
}