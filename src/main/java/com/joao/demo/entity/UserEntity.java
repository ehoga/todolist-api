package com.joao.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "users")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String role;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY
         , cascade = CascadeType.ALL
    )
    List<Todo> todos = new ArrayList<>();

}
