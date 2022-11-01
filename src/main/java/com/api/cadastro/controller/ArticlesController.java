package com.api.cadastro.controller;

import com.api.cadastro.controller.dtos.ArticleDto;
import com.api.cadastro.model.Articles;
import com.api.cadastro.service.ArticlesServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/Articles")
public class ArticlesController {
    private final ArticlesServiceImpl articlesService;

    public ArticlesController(ArticlesServiceImpl articlesService) {
        this.articlesService = articlesService;
    }

    @PostMapping
    public ResponseEntity adicionarArticle(@RequestBody ArticleDto articlesDto){
       if (articlesDto.getTitle() == null || articlesDto.getText() == null || articlesDto.getResume() == null){
              return (ResponseEntity.status(HttpStatus.NOT_FOUND).body("Os campos Title, Text e Resume não posem ser nulos."));
       } else if (articlesDto.getTitle().length() < 30 || articlesDto.getTitle().length() > 70) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Title deve ter tamanho mínimo de 30 caracteres e tamanho máximo de até 70 caracteres.");
       } else if (articlesDto.getResume().length() < 50 || articlesDto.getResume().length() > 100) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resumo deve ter tamanho mínimo de 50 caracteres e tamanho máximo de até 100 caracteres.");
       }else if(articlesDto.getText().length() < 200){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Text deve ter no mínimo 200 caracteres.");
       } else if(articlesService.verifyText(articlesDto.getTitle()) == true) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Title já existe, tente novamente.");
       }else if(articlesService.verifyUsername(articlesDto.getUserArt().getUsername()) == false){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Username não encontrado.");
       }else if(articlesService.verifyUuidUser(articlesDto.getUserArt().getUuidUser()) == false){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Uuid está incorreto.");
       } else {
           Articles articles = articlesService.addArticle(articlesDto);
           return ResponseEntity.status(HttpStatus.OK).body(articles);
       }
    }

    @GetMapping("/{uuid}")
    public List<Articles> GetArticle (@PathVariable("uuid") String uuid){
        List<Articles> article = articlesService.findArticle(uuid);
        return article;
    }

    @GetMapping
    public List<Articles> GetAllArticles(){
        List<Articles> article = articlesService.findAllArticles();
        return article;
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity deleteArticle(@PathVariable("uuid") String uuid){
        articlesService.deleteArticle(uuid);
        return ResponseEntity.status(HttpStatus.OK).body("Articles deletado com sucesso.");
    }

}

