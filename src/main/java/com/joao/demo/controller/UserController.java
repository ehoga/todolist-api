package com.joao.demo.controller;

import com.joao.demo.controller.mappers.UserMapper;
import com.joao.demo.dto.UserDTO;
import com.joao.demo.entity.UserEntity;
import com.joao.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper mapper;
    private final UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserEntity> entities = service.list();
        List<UserDTO> dtos = entities.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody UserDTO dto){
        var user = mapper.toEntity(dto);
        service.salvar(user);
        return ResponseEntity.created(URI.create("/users/" + user.getId()))
                .build();
    }
}
