package com.api.cadastro.repository;

import com.api.cadastro.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    List<Users> findByUuidContains(String uuid);
    Optional<Users> findByUsernameContains(String nome);
    boolean existsByUsernameContains(String username);
    boolean existsByUuidContains(String uuuid);
}
