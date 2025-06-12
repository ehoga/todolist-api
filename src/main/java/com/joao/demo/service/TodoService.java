package com.joao.demo.service;

import com.joao.demo.controller.mappers.TodoMapper;
import com.joao.demo.dto.TodoRequestDTO;
import com.joao.demo.dto.TodoResponseDTO;
import com.joao.demo.entity.Todo;
import com.joao.demo.entity.UserEntity;
import com.joao.demo.exception.UnauthorizedAccessException;
import com.joao.demo.repository.TodoRepository;
import com.joao.demo.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoService {

	private final UserRepository userRepository;
	private final TodoMapper todoMapper;
	private final TodoRepository todoRepository;
	
	public TodoService(TodoRepository todoRepository, UserRepository userRepository, TodoMapper todoMapper) {
		this.todoRepository = todoRepository;
		this.userRepository = userRepository;
		this.todoMapper = todoMapper;
	}

	public TodoResponseDTO create(TodoRequestDTO todoRequestDTO, String username) {
		Todo todo = todoMapper.toEntity(todoRequestDTO);

		UserEntity userEntity = userRepository.findByUsername(username);
		todo.setUserEntity(userEntity);

		if (todo.getPriority() == null){
			Integer maxPriority = todoRepository.findMaxPriority();
			todo.setPriority((maxPriority != null ? maxPriority : 0) + 1);
		}
		Todo savedTodo = todoRepository.save(todo);
        return todoMapper.toDTO(savedTodo);
	}

	public List<TodoResponseDTO> list(String username) {
		Sort sort = Sort.by("id").ascending();
		return todoRepository.findAllbyUser(username, sort)
				.stream()
				.map(todoMapper::toDTO)
				.collect(Collectors.toList());
	}

	public TodoResponseDTO update(Long id, TodoRequestDTO dto, String username) {
		Optional<Todo> existingTodoOpt = todoRepository.findById(id);
		Todo existing = null;

		if (existingTodoOpt.isPresent()){
			existing = existingTodoOpt.get();

			if (!existing.getUserEntity().getUsername().equals(username)) throw new UnauthorizedAccessException("Usuario nao autorizado");

			if(dto.title() != null) existing.setTitle(dto.title());
			if(dto.description() != null) existing.setDescription(dto.description());
			if(dto.done() != null) existing.setDone(dto.done());
			if(dto.priority() != null) existing.setPriority(dto.priority());

			todoRepository.save(existing);
		}

		if(existing == null) throw new UnauthorizedAccessException("Todo não encontrado");
		return todoMapper.toDTO(existing);
	}

	public List<TodoResponseDTO> delete(Long id, String username) {
		Optional<Todo> existingTodoOpt = todoRepository.findById(id);


		if (existingTodoOpt.isPresent()) {
			Todo existing = existingTodoOpt.get();



			if (existing.getUserEntity().getUsername().equals(username)) {
				todoRepository.delete(existing);
			}else {
				throw new UnauthorizedAccessException("Usuario nao autorizado");
			}
		}

		return list(username);
	}

}
