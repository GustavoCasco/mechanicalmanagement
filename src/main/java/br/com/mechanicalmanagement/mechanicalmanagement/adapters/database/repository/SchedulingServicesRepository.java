package br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.repository;

import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.SchedulingServicesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SchedulingServicesRepository extends JpaRepository<SchedulingServicesEntity, Long> {
    List<SchedulingServicesEntity> findByDateSchedule(LocalDate dateSchedule);

    Optional<SchedulingServicesEntity> findByDateScheduleAndTimetableSchedule(LocalDate dateSchedule, LocalTime schedule);
    boolean existsByDateScheduleAndTimetableSchedule(LocalDate dateSchedule, LocalTime schedule);
}
