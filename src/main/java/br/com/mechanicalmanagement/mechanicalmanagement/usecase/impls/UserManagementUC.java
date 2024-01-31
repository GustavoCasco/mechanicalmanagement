package br.com.mechanicalmanagement.mechanicalmanagement.usecase.impls;

import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.AccessControlEntity;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.TypeAccessControlEntity;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.entity.UserEntity;
import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.repository.UserRepository;
import br.com.mechanicalmanagement.mechanicalmanagement.dtos.UserDTO;
import br.com.mechanicalmanagement.mechanicalmanagement.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserManagementUC {

    private final UserRepository userRepository;

    public void addingNewUser(UserDTO userDTO) {

        var isUserExists = userRepository.findByAccessControlEntityDocumentNumber(userDTO.documentNumber());

        if (isUserExists.isPresent()) {
            throw new UserNotFoundException("Usuario já existe");
        }

        userRepository.save(UserEntity.builder()
                .accessControlEntity(AccessControlEntity.builder()
                        .secret_pwd(userDTO.secret_pwd())
                        .email(userDTO.email())
                        .documentNumber(userDTO.documentNumber())
                        .build())
                .userName(userDTO.userName())
                .typeAccessControlEntity(TypeAccessControlEntity.builder()
                        .idTypeUser(1)
                        .build())
                .numberPhone(userDTO.numberPhone())
                .build());
    }
}
