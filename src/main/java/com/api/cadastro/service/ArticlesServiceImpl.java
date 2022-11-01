package com.api.cadastro.service;

import com.api.cadastro.controller.dtos.ArticleDto;
import com.api.cadastro.metodoUtil.CreatUuid;
import com.api.cadastro.model.Articles;
import com.api.cadastro.model.Users;
import com.api.cadastro.repository.ArticlesRepository;
import com.api.cadastro.repository.UsersRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import static com.api.cadastro.metodoUtil.Slug.slug;

@Service
public class ArticlesServiceImpl {

    private final ArticlesRepository articlesRepository;
    private final UsersRepository usersRepository;

    public ArticlesServiceImpl(ArticlesRepository articlesRepository, UsersRepository usersRepository) {
        this.articlesRepository = articlesRepository;
        this.usersRepository = usersRepository;
    }

    public boolean verifyText(String title) {
        boolean existTitle = articlesRepository.existsByTitleContains(title);
        return existTitle;
    }

    public boolean verifyUsername(String username) {
        boolean verifyUsername = usersRepository.existsByUsernameContains(username);
        return verifyUsername;
    }

    public boolean verifyUuidUser(String uuid) {
        boolean verifyUuid = usersRepository.existsByUuidContains(uuid);
        return verifyUuid;
    }

    public Articles addArticle(ArticleDto article) {
        Articles newArtcle = article.transformToObject();

        newArtcle.setTitle(article.getTitle());
        newArtcle.setResume(article.getResume());
        newArtcle.setText(article.getText());
        String slug = slug(article.getTitle());
        newArtcle.setSlug(slug);
        newArtcle.setRegisteredAt(LocalDateTime.now());
        newArtcle.setUuid(new CreatUuid().creatUuid());

        return articlesRepository.save(newArtcle);
    }

    public List<Articles> findAllArticles() {
        List<Articles> article = articlesRepository.findAll();
        return article;
    }

    public List<Articles> findArticle(String uuid){
        List<Articles> article = articlesRepository.findByUuidContains(uuid);
        return article;
    }

    public void deleteArticle(String uuid) {
        List<Articles> article = articlesRepository.findByUuidContains(uuid);
        articlesRepository.deleteAllInBatch(article);
    }
}
