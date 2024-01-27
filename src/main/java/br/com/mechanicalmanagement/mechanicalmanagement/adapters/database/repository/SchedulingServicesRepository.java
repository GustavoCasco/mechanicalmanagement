package br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.repository;

import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.SchedulingServicesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface SchedulingServicesRepository extends CrudRepository<SchedulingServicesEntity, Long> {

    List<SchedulingServicesEntity> findByDateSchedule(Date dateSchedule);
}
