package com.api.cadastro.controller.dtos;
import com.api.cadastro.model.Articles;
import com.api.cadastro.model.Users;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Embedded;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Builder
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
public class ArticleDto {
    // Classe destinada para pegar os dados necessarios do artigo a ser adicionado.
    private String title;
    private String resume;
    private String text;
    @Embedded
    private UserDtoNameUuid userArt;

    public Articles transformToObject(){
        return new Articles(title, resume, text, getUserArt());
    }

}
