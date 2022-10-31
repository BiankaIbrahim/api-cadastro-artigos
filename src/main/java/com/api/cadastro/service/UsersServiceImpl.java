package com.api.cadastro.service;

import com.api.cadastro.controller.dtos.UserDto;
import com.api.cadastro.controller.dtos.UserDtoResponse;
import com.api.cadastro.metodoUtil.CreatUuid;
import com.api.cadastro.model.Users;
import com.api.cadastro.repository.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDateTime;
import java.util.List;
;

@Service
public class UsersServiceImpl {
    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public boolean verifyUsername(String username) {
        boolean existUsername = usersRepository.existsByUsernameContains(username);
        return existUsername;
    }

    public boolean verifyUuidUser(String uuid) {
        boolean verifyUuid = usersRepository.existsByUuidContains(uuid);
        return verifyUuid;
    }

    public Users addUser(UserDto user){
        Users addUser = user.transformToObject();
        addUser.setRegisteresdArt(LocalDateTime.now());
        addUser.setUuid(new CreatUuid().creatUuid());
        return usersRepository.save(addUser);
    }

    public List<UserDtoResponse> getAllUsers() {
            List<Users> users = usersRepository.findAll();
            return UserDtoResponse.convertListUsers(users);
    }
    public List<Users> getUser(String uuid) {
       return usersRepository.findByUuidContains(uuid);
    }

    public void deleteUser(String uuid) {
        List<Users> user = usersRepository.findByUuidContains(uuid);
        usersRepository.deleteAllInBatch(user);
    }
}
