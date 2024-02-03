package br.com.mechanicalmanagement.mechanicalmanagement.dtos;

public record UserDTO(
        String email,
        String secret_pwd,
        long documentNumber,
        String userName,
        long numberPhone
) {
}
