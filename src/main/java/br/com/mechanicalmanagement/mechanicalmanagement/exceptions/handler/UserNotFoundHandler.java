package br.com.mechanicalmanagement.mechanicalmanagement.exceptions.handler;

import br.com.mechanicalmanagement.mechanicalmanagement.exceptions.UserNotFoundException;
import br.com.mechanicalmanagement.mechanicalmanagement.exceptions.dto.ReturnExceptionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class UserNotFoundHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ReturnExceptionDTO> UserNotFound (UserNotFoundException e)
    {
        ReturnExceptionDTO error = new ReturnExceptionDTO(LocalDateTime.now(), e.getMessage());
        return ResponseEntity.status(400).body(error);
    }
}
