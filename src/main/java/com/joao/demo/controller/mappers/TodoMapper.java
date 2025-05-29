package com.joao.demo.controller.mappers;

import com.joao.demo.dto.TodoRequestDTO;
import com.joao.demo.dto.TodoResponseDTO;
import com.joao.demo.entity.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TodoMapper {

   Todo toEntity(TodoRequestDTO todoRequestDTO);

    @Mapping(source = "userEntity.username", target = "username")
    TodoResponseDTO toDTO(Todo todo);

}
