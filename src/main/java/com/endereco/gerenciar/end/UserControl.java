package com.endereco.gerenciar.end;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserControl {

    @Autowired
    UserRepository userRepository;
    @Autowired
    EnderecoRepository enderecoRepository;

    @PostMapping("/user")
    public ResponseEntity user (@RequestBody UserDto userDto){
        User user = new User();
        user.setNome(userDto.getNome());
        user.setCpf(userDto.getCpf());
        User newUser = userRepository.save(user);

        List<Endereco> enderecos = userDto.getEnderecos().stream().map(dto -> {
            Endereco endereco = new Endereco();
            endereco.setCEP(dto.getCep());
            endereco.setRua(dto.getRua());
            endereco.setNumero(dto.getNumero());
            endereco.setUsers(newUser);
            return endereco;
        }).collect(Collectors.toList());


        enderecoRepository.saveAll(enderecos);

        newUser.setEnderecos(enderecos);

        return ResponseEntity.status(HttpStatus.OK).body(newUser);

    }
    @GetMapping("/Userlistar")
    public List<User> userList() {
        return userRepository.findAll();
    }
}
