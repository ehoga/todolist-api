package com.joao.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joao.demo.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{

}
