package com.api.cadastro.model;
import com.api.cadastro.controller.dtos.UserDtoNameUuid;
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
public class Articles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String uuid;
    @Column(unique = true)
    private String title;
    @Column
    private String resume;
    @Column(unique = true)
    private String text;
    @Column
    private String slug;
    @Column
    private LocalDateTime registeredAt;
    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "user_id")
    private Long userId;

    public Articles(String title, String resume, String text, UserDtoNameUuid userArt) {
    }
}
