package br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.repository;

import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.AppointmentTimesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentTimesRepository extends CrudRepository<AppointmentTimesEntity, Long> {

    List<AppointmentTimesEntity> findByServicesEntityService(String scheduleName);
}
