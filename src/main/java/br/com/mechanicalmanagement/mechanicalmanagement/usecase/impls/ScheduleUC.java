package br.com.mechanicalmanagement.mechanicalmanagement.usecase.impls;

import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.AppointmentTimesEntity;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.SchedulingServicesEntity;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.repository.AppointmentTimesRepository;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.repository.SchedulingServicesRepository;
import br.com.mechanicalmanagement.mechanicalmanagement.dtos.ScheduleDTO;
import br.com.mechanicalmanagement.mechanicalmanagement.exceptions.ScheduleIsExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.TreeSet;

@Service
@RequiredArgsConstructor
public class ScheduleUC {
    private final SchedulingServicesRepository schedulingServicesRepository;
    private final AppointmentTimesUC appointmentTimesUC;
    private final AppointmentTimesRepository appointmentTimesRepository;
    private HashMap<Integer, LocalTime> timeControl;
    private TreeSet<LocalTime> filteredListAccordingAvailability;
    private int count = 0;

    public TreeSet<LocalTime> findAllScheduleAvailable(String serviceName, LocalDate dateSchedule) {
        count = 0;
        timeControl = new HashMap<>();
        filteredListAccordingAvailability = new TreeSet<>();

        var listScheduleForDate = schedulingServicesRepository.findByDateSchedule(dateSchedule);
        appointmentTimesRepository.findByServicesEntityService(serviceName)
                .stream()
                .map(AppointmentTimesEntity::getSchedule)
                .forEach(appointmentTime -> {
                    timeControl.put(count, appointmentTime);
                    if (!listScheduleForDate.isEmpty()) {
                        listScheduleForDate.forEach(schedule -> {
                            if ((timeControl.get(count == 0 ? 0 : count - 1).isBefore(schedule.getTimetable().getSchedule())
                                    && appointmentTime.getHour() < schedule.getTimetable().getSchedule().getHour())
                                    != appointmentTime.isAfter(schedule.getTimetable().getSchedule())) {
                                filteredListAccordingAvailability.add(appointmentTime);
                            }
                        });
                    } else {
                        filteredListAccordingAvailability.add(appointmentTime);
                    }
                    count++;
                });
        return filteredListAccordingAvailability;
    }

    //TODO: ADICIONAR UPDATE HORARIO E DATA DO AGENDAMENTO

    public void updateStatus(ScheduleDTO scheduleDTO) {
        schedulingServicesRepository.findByDateScheduleAndTimetableSchedule(scheduleDTO.dateSchedule(),
                scheduleDTO.schedule()).ifPresent(schedulingServicesEntity -> {
            if (scheduleDTO.id_status() != 0) {
                schedulingServicesEntity.setId_status(scheduleDTO.id_status());
            }
            schedulingServicesRepository.save(schedulingServicesEntity);
        });
    }

    public void saveSchedule(ScheduleDTO scheduleDTO) {
        var timetable = appointmentTimesUC.findByHoursAndService(scheduleDTO.id_Service(), scheduleDTO.schedule());
        var isExistsSchedule = schedulingServicesRepository
                .existsByDateScheduleAndTimetableSchedule(scheduleDTO.dateSchedule(),
                        scheduleDTO.schedule());
        if (!isExistsSchedule) {
            schedulingServicesRepository.save(SchedulingServicesEntity.builder()
                    .dateSchedule(scheduleDTO.dateSchedule())
                    .id_User(scheduleDTO.id_User())
                    .id_status(1)
                    .id_Services(scheduleDTO.id_Service())
                    .timetable(timetable)
                    .build());
        } else {
            throw new ScheduleIsExistsException("Data já possui um agendamento");
        }
    }
}
