package br.com.mechanicalmanagement.mechanicalmanagement.usecase.impls;

import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.AppointmentTimesEntity;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.ServicesEntity;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.repository.AppointmentTimesRepository;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.repository.SchedulingServicesRepository;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.repository.ServicesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalTime;
import java.util.*;

import static org.apache.commons.lang3.StringUtils.leftPad;

@Service
@RequiredArgsConstructor
public class AppointmentTimesUC {

    private final AppointmentTimesRepository appointmentTimesRepository;
    private final ServicesRepository servicesRepository;
    private final SchedulingServicesRepository schedulingServicesRepository;
    private HashMap<Integer, LocalTime> timeControl;
    private List<LocalTime> filteredListAccordingAvailability;
    private int count = 0;

    public void saveAppointmentTimes(String serviceName, LocalTime scheduleEnd, int totalServiceTime) {
        servicesRepository.findByService(serviceName).ifPresent(service -> {
            appointmentTimesRepository.saveAll(createdTableSchedule(scheduleEnd, totalServiceTime, service.getIdServices()));
        });
    }

    public AppointmentTimesEntity findByHoursAndService(Long id_Service, LocalTime hours){
            return appointmentTimesRepository.findByServicesEntityIdServicesAndSchedule(id_Service,
                hours).get();
    }

    public List<LocalTime> findAllScheduleAvailable(String serviceName, Date dateSchedule) {
        count = 0;
        timeControl =  new HashMap<>();
        filteredListAccordingAvailability = new ArrayList<>();

        var listScheduleForDate = schedulingServicesRepository.findByDateSchedule(dateSchedule);
        appointmentTimesRepository.findByServicesEntityService(serviceName)
                .stream()
                .map(AppointmentTimesEntity::getSchedule)
                .sorted(LocalTime::compareTo)
                .forEach(appointmentTime -> {
                    timeControl.put(count, appointmentTime);
                    listScheduleForDate.forEach(schedule -> {
                        if ((timeControl.get(count == 0 ? 0 : count - 1).isBefore(schedule.getTimetable().getSchedule())
                                && appointmentTime.getHour() < schedule.getTimetable().getSchedule().getHour())
                                != appointmentTime.isAfter(schedule.getTimetable().getSchedule())){
                            filteredListAccordingAvailability.add(appointmentTime);
                        }
                    });
                    count++;
                });
        return filteredListAccordingAvailability;
    }

    private Set<AppointmentTimesEntity> createdTableSchedule(LocalTime scheduleEnd, int totalServiceTime, long idService) {
        Set<AppointmentTimesEntity> listAllScheduleForService = new HashSet<>();
        int minutesStart = 480;
        int minutesEnd = scheduleEnd.getHour() * 60;
        int hours = 8;
        for (int i = 0; minutesStart + totalServiceTime < minutesEnd; i++) {
            int valor = i == 0 ? minutesStart : minutesStart + totalServiceTime;
            int time = valor % 60;
            hours = (valor - time) / 60;
            listAllScheduleForService.add(AppointmentTimesEntity.builder()
                    .servicesEntity(ServicesEntity.builder().idServices(idService).build())
                            .serviceTime(totalServiceTime)
                    .schedule(LocalTime.parse(leftPad(String.valueOf(hours), 2, "0")
                            .concat(":")
                            .concat(leftPad(String.valueOf(time), 2, "0"))))
                    .build());
            minutesStart = (hours * 60) + time;
        }
        return listAllScheduleForService;
    }
}
