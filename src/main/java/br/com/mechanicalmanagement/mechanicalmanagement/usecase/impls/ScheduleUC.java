package br.com.mechanicalmanagement.mechanicalmanagement.usecase.impls;

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
        var timetable = appointmentTimesUC.findByHoursAndService(scheduleDTO.id_Service(), scheduleDTO.schedule());
        var isExistsSchedule = schedulingServicesRepository
                .existsByDateScheduleAndTimetableSchedule(scheduleDTO.dateSchedule(),
                        scheduleDTO.schedule());
        if(!isExistsSchedule){
            schedulingServicesRepository.save(SchedulingServicesEntity.builder()
                    .dateSchedule(scheduleDTO.dateSchedule())
                    .id_User(scheduleDTO.id_User())
                    .id_Services(scheduleDTO.id_Service())
                    .timetable(timetable)
                    .build());
        }
        //TODO: colocar uma exception com retorno 400 bad request
    }
}
