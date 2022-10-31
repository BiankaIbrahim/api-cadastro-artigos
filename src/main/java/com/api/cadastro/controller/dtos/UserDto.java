package com.api.cadastro.controller.dtos;

import com.api.cadastro.model.Users;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
public class UserDto {
    // Classe destinada para pegar os dados necessarios do novo usuario.
    private String username;
    private String password;

    public Users transformToObject(){
        return new Users(username, password);
    }


    public Users transformToObject(List<Users> userAtual) {
        return new Users();
    }
}
