package br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.repository;

import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.TypeAccessControlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeAccessControlRepository extends JpaRepository<TypeAccessControlEntity, Long> {
}
