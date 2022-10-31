package com.api.cadastro.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String uuid;
    @Column(unique = true)
    private String username;
    @Column
    private String password;
    @Column
    private LocalDateTime registeresdArt;

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setRegisteresdArt(final LocalDateTime registeresdArt) {
        this.registeresdArt = registeresdArt;
    }

}
