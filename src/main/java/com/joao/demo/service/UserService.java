package com.joao.demo.service;

import com.joao.demo.entity.UserEntity;
import com.joao.demo.repository.UserRepository;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public List<UserEntity> list(){
        return repository.findAll();
    }

    public void salvar(UserEntity user){
        var password = user.getPassword();
        user.setPassword(encoder.encode(password));
        repository.save(user);
    }

    public UserEntity obterPorLogin(String username){
        return repository.findByUsername(username);
    }
}