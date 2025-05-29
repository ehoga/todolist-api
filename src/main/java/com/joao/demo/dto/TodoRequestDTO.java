package com.joao.demo.dto;

public record TodoRequestDTO(Long id,
                             String title,
                             String description,
                             Boolean done,
                             Integer priority) {
}
