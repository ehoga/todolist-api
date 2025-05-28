package com.joao.demo.repository;

import com.joao.demo.entity.Todo;
import com.joao.demo.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByUser(User user, Sort sort);
    
    @Query("SELECT MAX(t.priority) FROM Todo t WHERE t.user.id = :userId")
    Integer findMaxPriorityByUser(@Param("userId") UUID userId);

    boolean existsByTitle(String title);
}
