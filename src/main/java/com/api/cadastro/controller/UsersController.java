package com.api.cadastro.controller;
import com.api.cadastro.controller.dtos.UserDto;
import com.api.cadastro.controller.dtos.UserDtoResponse;
import com.api.cadastro.model.Users;
import com.api.cadastro.repository.UsersRepository;
import com.api.cadastro.service.UsersServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/User")
public class UsersController {
    private final UsersServiceImpl usersService;

    private final PasswordEncoder encoder;

    public UsersController(UsersServiceImpl usersService, UsersRepository usersRepository, PasswordEncoder encoder) {
        this.usersService = usersService;
        this.encoder = encoder;
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody UserDto userDTO) {
        if(userDTO.getUsername() == null || userDTO.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Username ou password não podem ser nulos");
        } else if (userDTO.getUsername().length() < 3 || userDTO.getUsername().length() > 150) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("username deve ter tamanho mínimo de 3 caracteres e tamanho máximo de até 150 caracteres.");
        } else if (usersService.verifyUsername((userDTO.getUsername())) == true) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O username já existe, tente novamente :).");
        } else if (userDTO.getPassword().length() < 8) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Password deve ter no mínimo 8 caracteres.");
        } else {
            userDTO.setPassword(encoder.encode(userDTO.getPassword()));
            Users addUser = usersService.addUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(UserDtoResponse.UserDtoResponse(addUser));
        }
    }

    @GetMapping()
    public List<UserDtoResponse> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity findByUuid(@PathVariable("uuid") String uuid) {
          List<Users> user = usersService.getUser(uuid);
          return ResponseEntity.status(HttpStatus.OK).body(UserDtoResponse.convertListUsers(user));
   }


      @DeleteMapping("/{uuid}")
      public ResponseEntity removeUser(@PathVariable("uuid") String uuid) {
          usersService.deleteUser(uuid);
          return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso.");
    }
}

    /*

    @PutMapping("/{uuid}")
    public ResponseEntity updateUser(@PathVariable("uuid") String uuid, @RequestBody UserDto userDTO) {
        if (userDTO.getUsername() == null || userDTO.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Username ou password não podem ser nulos");
        } else if (userDTO.getUsername().length() < 3 || userDTO.getUsername().length() > 150) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("username deve ter tamanho mínimo de 3 caracteres e tamanho máximo de até 150 caracteres.");
        } else if (userDTO.getPassword().length() < 8) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Password deve ter no mínimo 8 caracteres.");
        } else if (usersService.verifyUuidUser(uuid) == false) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }

           //userDTO.setPassword(encoder.encode(userDTO.getPassword())); // criptografia da senha.
           Users user = usersService.updateUser(uuid, userDTO);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(UserDtoResponse.UserDtoResponse(user));
    }

    */







