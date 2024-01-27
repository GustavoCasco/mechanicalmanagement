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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.leftPad;

@Service
@RequiredArgsConstructor
public class AppointmentTimesUC {

    private final AppointmentTimesRepository appointmentTimesRepository;
    private final ServicesRepository servicesRepository;
    private final SchedulingServicesRepository schedulingServicesRepository;

    public void saveAppointmentTimes(String serviceName, LocalTime scheduleEnd, int totalServiceTime) {
        servicesRepository.findByService(serviceName).ifPresent(service -> {
            appointmentTimesRepository.saveAll(createdTableSchedule(scheduleEnd, totalServiceTime, service.getId_Services()));
        });
    }

    public List<LocalTime> findAllScheduleAvailable(String serviceName, Date dateSchedule) {
        var a = schedulingServicesRepository.findByDateSchedule(dateSchedule);

        //TODO: PEGAR OBJETO DE AGENDAMENTO E FAZER A SEGUINTE VALIDAÇÃO:
        // VERIFICAR SE OS HORARIOS DISPONIVEIS ESTÃO ENTRE O TERMINO E O INICIO DO AGENDAMENTO CASO SIM, REMOVER ESSE HORARIO
        return appointmentTimesRepository.findByServicesEntityService(serviceName)
                .stream().map(AppointmentTimesEntity::getSchedule)
                .collect(Collectors.toList());
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
                    .servicesEntity(ServicesEntity.builder().id_Services(idService).build())
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
