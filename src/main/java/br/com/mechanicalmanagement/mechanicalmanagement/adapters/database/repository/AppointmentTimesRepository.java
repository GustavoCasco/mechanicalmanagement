package br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.repository;

import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.AppointmentTimesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentTimesRepository extends JpaRepository<AppointmentTimesEntity, Long> {

    List<AppointmentTimesEntity> findByServicesEntityService(String scheduleName);

    @Query(nativeQuery = true, value = "select * from tbhg_horarios a where a.id_servicos =:idServices and a.horario =:schedule ;")
    List<AppointmentTimesEntity> findHoursByService(@Param(value = "idServices") Long idServices,
                                                    @Param(value = "schedule") LocalTime schedule);
}
