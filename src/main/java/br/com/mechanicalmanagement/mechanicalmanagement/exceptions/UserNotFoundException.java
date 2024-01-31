package br.com.mechanicalmanagement.mechanicalmanagement.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException (String mensagem) {
        super(mensagem);
    }
}
