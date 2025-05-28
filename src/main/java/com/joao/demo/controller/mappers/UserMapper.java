package com.joao.demo.controller.mappers;

import com.joao.demo.dto.UserDTO;
import com.joao.demo.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(UserDTO dto);

    UserDTO toDTO(UserEntity entity);
}
