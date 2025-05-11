package com.joao.demo.repository;

import com.joao.demo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TodoRepository extends JpaRepository<Todo, Long>{

    @Query("SELECT MAX(t.priority) FROM Todo t")
    Integer findMaxPriority();
}
