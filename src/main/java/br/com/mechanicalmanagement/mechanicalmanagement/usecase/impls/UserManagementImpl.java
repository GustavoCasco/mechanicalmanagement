package br.com.mechanicalmanagement.mechanicalmanagement.usecase.impls;

import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.UserEntity;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.repository.UserRepository;
import br.com.mechanicalmanagement.mechanicalmanagement.dtos.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManagementImpl {

    private final UserRepository userRepository;

    //TODO: FAZER FUNCIONAR O SALVAMENTO DE USUARIO
    public void addingNewUser(UserDTO userDTO){
        userRepository.save(UserEntity.builder()
                .userName(userDTO.getUserName())
                .numberPhone(userDTO.getNumberPhone())
                .build());
    }
}
