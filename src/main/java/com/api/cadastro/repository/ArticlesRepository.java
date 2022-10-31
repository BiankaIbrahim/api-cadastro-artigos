package com.api.cadastro.repository;

import com.api.cadastro.model.Articles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ArticlesRepository extends JpaRepository<Articles, Long> {
    boolean existsByTitleContains(String title);
    List<Articles> findByUuidContains(String title);

}
