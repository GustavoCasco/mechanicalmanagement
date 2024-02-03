package br.com.mechanicalmanagement.mechanicalmanagement.usecase.impls;

import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.PriceEntity;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.ServicesEntity;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.UserEntity;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.repository.PriceRepository;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.repository.ServicesRepository;
import br.com.mechanicalmanagement.mechanicalmanagement.dtos.ServicesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServicesUC {

    private final ServicesRepository servicesRepository;
    private final PriceRepository priceRepository;
    private final ScheduleUC scheduleUC;

    public List<ServicesDTO> listAllServices(LocalDate dateSearch) {
        return servicesRepository.findAll().stream()
                .map(x -> this.converterDTOInEntity(x, dateSearch))
                .collect(Collectors.toList());
    }

    public void saveServices(ServicesDTO servicesDTO) {
        servicesRepository.findByService(servicesDTO.service())
                .ifPresentOrElse(servicesEntity -> {
                    if (servicesDTO.price() != servicesEntity.getPriceEntity().getPrice()) {
                        checkPriceExists(servicesDTO.price()).ifPresent(servicesEntity::setPriceEntity);
                        servicesRepository.save(servicesEntity);
                    }
                }, () -> {
                    ServicesEntity servicesEntity = new ServicesEntity();
                    servicesEntity.setId_User(UserEntity.builder().idUser(servicesDTO.idUserResponse()).build());
                    checkPriceExists(servicesDTO.price()).ifPresent(servicesEntity::setPriceEntity);
                    servicesEntity.setDescriptionService(servicesDTO.descriptionService());
                    servicesEntity.setService(servicesDTO.service());
                    servicesRepository.save(servicesEntity);
                });
    }

    private void savePriceInTable(boolean existsPrice, double price) {
        if (!existsPrice) {
            priceRepository.save(PriceEntity.builder().price(price).build());
        }
    }

    private Optional<PriceEntity> checkPriceExists(double price) {
        var existsPrice = priceRepository.existsByPrice(price);
        savePriceInTable(existsPrice, price);
        return priceRepository.findByPrice(price);
    }

    private ServicesDTO converterDTOInEntity(ServicesEntity servicesEntity, LocalDate dateSearch) {

        return new ServicesDTO(servicesEntity.getIdServices(),
                servicesEntity.getService(),
                servicesEntity.getDescriptionService(),
                null,
                servicesEntity.getAppointmentTimes().stream().findFirst().get().getServiceTime(),
                servicesEntity.getPriceEntity().getPrice(),
                servicesEntity.getId_User().getIdUser(),
                scheduleUC.findAllScheduleAvailable(servicesEntity.getService(), dateSearch));
    }
}
