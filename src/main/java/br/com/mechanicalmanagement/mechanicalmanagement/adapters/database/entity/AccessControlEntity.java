package br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tbhg_acesso")
public class AccessControlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_acesso")
    private long id_Access;
    private String email;
    @Column(name = "palavra_chave")
    private String secret_pwd;
    @Column(name = "numero_documento")
    private long documentNumber;

    @OneToOne(mappedBy = "accessControlEntity")
    private UserEntity userEntity;

}
