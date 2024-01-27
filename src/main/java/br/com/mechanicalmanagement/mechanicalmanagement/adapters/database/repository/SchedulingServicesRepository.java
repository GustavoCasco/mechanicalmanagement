package br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.repository;

import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.SchedulingServicesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SchedulingServicesRepository extends JpaRepository<SchedulingServicesEntity, Long> {

    List<SchedulingServicesEntity> findByDateSchedule(LocalDate dateSchedule);
}
