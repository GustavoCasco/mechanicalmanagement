package br.com.mechanicalmanagement.mechanicalmanagement.usecase.impls;

import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.AppointmentTimesEntity;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.SchedulingServicesEntity;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.repository.SchedulingServicesRepository;
import br.com.mechanicalmanagement.mechanicalmanagement.dtos.ScheduleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleUC {
    private final SchedulingServicesRepository schedulingServicesRepository;
    private final AppointmentTimesUC appointmentTimesUC;

    public void saveSchedule(ScheduleDTO scheduleDTO){
        var a = appointmentTimesUC.findByHoursAndService(scheduleDTO.getId_Service(), scheduleDTO.getSchedule());
        schedulingServicesRepository.save(SchedulingServicesEntity.builder()
                        .dateSchedule(scheduleDTO.getDateSchedule())
                        .id_User(scheduleDTO.getId_User())
                        .id_Services(scheduleDTO.getId_Service())
                        .timetable(a)
                .build());
    }
}
