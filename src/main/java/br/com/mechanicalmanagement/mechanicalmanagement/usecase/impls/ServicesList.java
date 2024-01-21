package br.com.mechanicalmanagement.mechanicalmanagement.usecase.impls;

import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.PriceEntity;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.ServicesEntity;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.repository.PriceRepository;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.repository.ServicesRepository;
import br.com.mechanicalmanagement.mechanicalmanagement.dtos.ServicesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServicesList {

    private final ServicesRepository servicesRepository;
    private final PriceRepository priceRepository;

    //TODO: FAZER FUNCIONAR O SALVAMENTO DE SERVIÇOS JÁ ADICIONANDO UM USUARIO A ELE
    public void saveServices(ServicesDTO servicesDTO){
        ServicesEntity servicesEntity = new ServicesEntity();
//        servicesEntity.setId_User(UserEntity.builder().id_User(1L).id_TypeUser(1L).id_Access(1L).build());
        servicesEntity.setPriceEntity(PriceEntity.builder().id_Price(1L).price(servicesDTO.getPrice()).build());
        servicesEntity.setDescriptionService(servicesDTO.getDescriptionService());
        servicesEntity.setService(servicesDTO.getService());
        servicesRepository.save(servicesEntity);
    }
}
