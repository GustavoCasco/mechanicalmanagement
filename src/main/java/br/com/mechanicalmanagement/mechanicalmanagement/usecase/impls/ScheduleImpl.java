package br.com.mechanicalmanagement.mechanicalmanagement.usecase.impls;

import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.AppointmentTimesEntity;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.ServicesEntity;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.repository.AppointmentTimesRepository;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.repository.ServicesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static org.apache.commons.lang3.StringUtils.leftPad;

@Service
@RequiredArgsConstructor
public class ScheduleImpl {

    private final AppointmentTimesRepository appointmentTimesRepository;
    private final ServicesRepository servicesRepository;

    public void saveSchedule(String serviceName, LocalTime scheduleEnd, int totalServiceTime) {
        servicesRepository.findByService(serviceName).ifPresent(service -> {
            appointmentTimesRepository.saveAll(createdTableSchedule(scheduleEnd, totalServiceTime, service.getId_Services()));
        });
    }

    private Set<AppointmentTimesEntity> createdTableSchedule(LocalTime scheduleEnd, int totalServiceTime, long idService) {
        Set<AppointmentTimesEntity> listAllScheduleForService = new HashSet<>();
        appointmentTimesRepository.findBySchedule(scheduleEnd)
                .ifPresentOrElse(listAllScheduleForService::add,
                        () -> {
                            int minutosStart = 480;
                            int minutosEnd = scheduleEnd.getHour() * 60;
                            int horas = 8;
                            for (int i = 0; minutosStart + totalServiceTime < minutosEnd; i++) {
                                int valor = i == 0 ? minutosStart : minutosStart + totalServiceTime;
                                int time = valor % 60;
                                horas = (valor - time) / 60;
                                listAllScheduleForService.add(AppointmentTimesEntity.builder()
                                        .servicesEntity(ServicesEntity.builder().id_Services(idService).build())
                                        .schedule(LocalTime.parse(leftPad(String.valueOf(horas), 2, "0")
                                                .concat(":")
                                                .concat(leftPad(String.valueOf(time), 2, "0"))))
                                        .build());
                                minutosStart = (horas * 60) + time;
                            }
                        });
        return listAllScheduleForService;
    }
}
