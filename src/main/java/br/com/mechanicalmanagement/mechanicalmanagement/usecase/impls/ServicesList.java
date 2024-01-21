package br.com.mechanicalmanagement.mechanicalmanagement.usecase.impls;

import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.PriceEntity;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.ServicesEntity;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.UserEntity;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.repository.PriceRepository;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.repository.ServicesRepository;
import br.com.mechanicalmanagement.mechanicalmanagement.dtos.ServicesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServicesList {

    private final ServicesRepository servicesRepository;
    private final PriceRepository priceRepository;

    public void saveServices(ServicesDTO servicesDTO){

        servicesRepository.findByService(servicesDTO.getService())
                .ifPresentOrElse(servicesEntity -> {
                    if(servicesDTO.getPrice() != servicesEntity.getPriceEntity().getPrice()){
                        checkPriceExists(servicesDTO.getPrice()).ifPresent(servicesEntity::setPriceEntity);
                        servicesRepository.save(servicesEntity);
                    }
                }, () -> {
                    ServicesEntity servicesEntity = new ServicesEntity();
                    servicesEntity.setId_User(UserEntity.builder().id_User(2L).build());
                    checkPriceExists(servicesDTO.getPrice()).ifPresent(servicesEntity::setPriceEntity);
                    servicesEntity.setDescriptionService(servicesDTO.getDescriptionService());
                    servicesEntity.setService(servicesDTO.getService());
                    servicesRepository.save(servicesEntity);
                });
    }

    public void savePriceInTable(boolean existsPrice, double price){
        if (!existsPrice){
            priceRepository.save(PriceEntity.builder().price(price).build());
        }
    }

    public Optional<PriceEntity> checkPriceExists(double price){
        var existsPrice = priceRepository.existsByPrice(price);
        savePriceInTable(existsPrice, price);
        return priceRepository.findByPrice(price);
    }
}
