package com.api.cadastro.controller.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Embeddable
public class UserDtoNameUuid {

    private String username;
    private String uuidUser;
}
