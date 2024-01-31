package br.com.mechanicalmanagement.mechanicalmanagement.exceptions.dto;

import java.time.LocalDateTime;

public record ReturnExceptionDTO(
        LocalDateTime timestamp,
        String message
) {
}
