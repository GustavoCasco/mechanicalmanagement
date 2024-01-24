package br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tbhg_tipo_usuario")
public class TypeAccessControlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipousuario")
    private long id_TypeUser;
    @Column(name = "descricao_tipo_usuario")
    private String descriptionTypeUser;
    @OneToOne(mappedBy = "typeAccessControlEntity")
    private UserEntity userEntity;
}
