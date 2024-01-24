package br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tbhg_usuario")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private long id_User;
    @Column(name = "nome")
    private String userName;
    @Column(name = "celular")
    private long numberPhone;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_acesso")
    private AccessControlEntity accessControlEntity;
    @OneToOne
    @JoinColumn(name = "id_tipousuario")
    private TypeAccessControlEntity typeAccessControlEntity;
    @OneToMany(mappedBy="id_User")
    private List<ServicesEntity> servicesEntities;

}
