package br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Table
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tbhg_status")
public class StatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status")
    private long idStatus;
    @Column(name = "status")
    private String status;
}
