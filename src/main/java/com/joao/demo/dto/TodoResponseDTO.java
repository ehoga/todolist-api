package com.joao.demo.dto;

public record TodoResponseDTO(
        Long id,
        String title,
        String description,
        Boolean done,
        Integer priority,
        String username) {
}
