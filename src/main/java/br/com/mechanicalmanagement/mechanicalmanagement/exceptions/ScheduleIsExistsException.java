package br.com.mechanicalmanagement.mechanicalmanagement.exceptions;

public class ScheduleIsExistsException extends RuntimeException{

    public ScheduleIsExistsException(String mensagem) {
        super(mensagem);
    }
}
