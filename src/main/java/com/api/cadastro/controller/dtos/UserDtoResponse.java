package com.api.cadastro.controller.dtos;

import com.api.cadastro.model.Users;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDtoResponse {
    private String username;
    private String uuid;
    private LocalDateTime registeresdArt;

    public UserDtoResponse(Users user){
       this.username = user.getUsername();
       this.uuid = user.getUuid();
       this.registeresdArt = user.getRegisteresdArt();
    }

    public static List<UserDtoResponse>convertListUsers(List<Users> user){
        return user.stream().map(UserDtoResponse::new).collect(Collectors.toList());
    }

    public static UserDtoResponse UserDtoResponse(Users user){
        return new UserDtoResponse(user.getUsername(), user.getUuid(), user.getRegisteresdArt());
    }
}
