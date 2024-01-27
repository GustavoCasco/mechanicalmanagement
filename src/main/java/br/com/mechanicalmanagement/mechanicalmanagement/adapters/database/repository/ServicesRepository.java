package br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.repository;

import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.ServicesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServicesRepository extends JpaRepository<ServicesEntity, Long> {

    Optional<ServicesEntity> findByService(String service);
}
