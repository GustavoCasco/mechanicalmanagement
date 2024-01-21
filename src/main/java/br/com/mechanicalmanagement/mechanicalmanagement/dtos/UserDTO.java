package br.com.mechanicalmanagement.mechanicalmanagement.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDTO {

    private String email;
    private String secret_pwd;
    private long documentNumber;
    private String userName;
    private long numberPhone;
}
