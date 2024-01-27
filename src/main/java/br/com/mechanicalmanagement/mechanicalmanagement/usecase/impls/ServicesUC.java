package br.com.mechanicalmanagement.mechanicalmanagement.usecase.impls;

import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.PriceEntity;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.ServicesEntity;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.UserEntity;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.repository.AppointmentTimesRepository;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.repository.PriceRepository;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.repository.ServicesRepository;
import br.com.mechanicalmanagement.mechanicalmanagement.dtos.ServicesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServicesUC {

    private final ServicesRepository servicesRepository;
    private final PriceRepository priceRepository;
    private final AppointmentTimesRepository appointmentTimesRepository;

    public List<ServicesDTO> listAllServices() {
        return servicesRepository.findAll().parallelStream()
                .map(this::converterDTOInEntity)
                .collect(Collectors.toList());
    }

    public void saveServices(ServicesDTO servicesDTO) {
        servicesRepository.findByService(servicesDTO.getService())
                .ifPresentOrElse(servicesEntity -> {
                    if (servicesDTO.getPrice() != servicesEntity.getPriceEntity().getPrice()) {
                        checkPriceExists(servicesDTO.getPrice()).ifPresent(servicesEntity::setPriceEntity);
                        servicesRepository.save(servicesEntity);
                    }
                }, () -> {
                    ServicesEntity servicesEntity = new ServicesEntity();
                    servicesEntity.setId_User(UserEntity.builder().id_User(servicesDTO.getIdUserResponse()).build());
                    checkPriceExists(servicesDTO.getPrice()).ifPresent(servicesEntity::setPriceEntity);
                    servicesEntity.setDescriptionService(servicesDTO.getDescriptionService());
                    servicesEntity.setService(servicesDTO.getService());
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

    private ServicesDTO converterDTOInEntity(ServicesEntity servicesEntity) {
        return ServicesDTO.builder()
                .service(servicesEntity.getService())
                .descriptionService(servicesEntity.getDescriptionService())
                .price(servicesEntity.getPriceEntity().getPrice())
                .build();
    }
}
