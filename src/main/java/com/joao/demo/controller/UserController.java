package com.joao.demo.controller;

import com.joao.demo.entity.User;
import com.joao.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {this.userService = userService;}

    @GetMapping
    public List<User> getAllUsers(){
        return userService.list();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return userService.findById(id).map(ResponseEntity :: ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        return userService.update(id, user).map(updatedUser -> ResponseEntity.ok().body(updatedUser))
                .orElse(ResponseEntity.notFound().build());
    }

}
