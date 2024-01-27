package br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.repository;

import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByAccessControlEntityDocumentNumber(long cpf);
}
