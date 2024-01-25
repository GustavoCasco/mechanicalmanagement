package br.com.mechanicalmanagement.mechanicalmanagement.usecase.impls;

import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.AccessControlEntity;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.TypeAccessControlEntity;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.UserEntity;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.repository.UserRepository;
import br.com.mechanicalmanagement.mechanicalmanagement.dtos.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManagementUC {

    private final UserRepository userRepository;

    public void addingNewUser(UserDTO userDTO) {

        var isUserExists = userRepository.findByAccessControlEntityDocumentNumber(userDTO.getDocumentNumber());

        if (isUserExists.isPresent()) {
            //TODO: colocar uma exception com retorno 400 bad request mensagem: usuario existente
            return;
        }

        userRepository.save(UserEntity.builder()
                .accessControlEntity(AccessControlEntity.builder()
                        .secret_pwd(userDTO.getSecret_pwd())
                        .email(userDTO.getEmail())
                        .documentNumber(userDTO.getDocumentNumber())
                        .build())
                .userName(userDTO.getUserName())
                .typeAccessControlEntity(TypeAccessControlEntity.builder()
                        .id_TypeUser(1)
                        .build())
                .numberPhone(userDTO.getNumberPhone())
                .build());
    }
}
