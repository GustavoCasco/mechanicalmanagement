package br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.repository;

import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.AppointmentTimesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentTimesRepository extends JpaRepository<AppointmentTimesEntity, Long> {

    List<AppointmentTimesEntity> findByServicesEntityService(String scheduleName);
    Optional<AppointmentTimesEntity> findByServicesEntityIdServicesAndSchedule(Long id_Services, LocalTime schedule);
}
