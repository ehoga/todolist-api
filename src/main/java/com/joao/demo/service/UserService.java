package com.joao.demo.service;

import com.joao.demo.entity.User;
import com.joao.demo.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public Optional<User> update(Long id, User user) {
       return userRepository.findById(id).map(existingUser -> {
           existingUser.setNome(user.getNome());
           existingUser.setSenha(user.getSenha());
           return userRepository.save(existingUser);
       });
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> list() {
        return userRepository.findAll();
    }
}
