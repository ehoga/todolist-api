package com.joao.demo.service;

import com.joao.demo.config.SecurityUtils;
import com.joao.demo.entity.Todo;
import com.joao.demo.entity.User;
import com.joao.demo.repository.TodoRepository;
import com.joao.demo.repository.UserRepository;
import com.joao.demo.validator.TodoValidator;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
	
	private TodoRepository todoRepository;
	private TodoValidator todoValidator;
	private UserRepository userRepository;
	
	public TodoService(TodoRepository todoRepository, TodoValidator todoValidator, UserRepository userRepository) {
		this.todoRepository = todoRepository;
		this.todoValidator = todoValidator;
		this.userRepository = userRepository;
	}

	private User getCurrentUser() {
		String username = SecurityUtils.getCurrentUsername();
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não encontrado"));
	}

	private void validateOwnership(Todo todo) {
		if (!todo.getUser().getId().equals(getCurrentUser().getId())) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não tem permissão para acessar este Todo");
		}
	}

	public List<Todo> create(Todo todo) {
		todoValidator.validate(todo);
		todo.setUser(getCurrentUser());
		
		if (todo.getPriority() == null){
			Integer maxPriority = todoRepository.findMaxPriorityByUser(getCurrentUser().getId());
			todo.setPriority((maxPriority != null ? maxPriority : 0) + 1);
		}
		todoRepository.save(todo);
		return list();
	}

	public Todo findById(Long id){
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo não encontrado"));
		validateOwnership(todo);
		return todo;
	}

	public List<Todo> list() {
		Sort sort = Sort.by("priority").ascending().and(Sort.by("title").ascending());
		return todoRepository.findAllByUser(getCurrentUser(), sort);
	}

	public List<Todo> update(Todo todo) {
		Todo existing = findById(todo.getId());
		validateOwnership(existing);

		if(todo.getTitle() != null) existing.setTitle(todo.getTitle());
		if(todo.getDescription() != null) existing.setDescription(todo.getDescription());
		if(todo.getDone() != null) existing.setDone(todo.getDone());
		if(todo.getPriority() != null) existing.setPriority(todo.getPriority());

		todoRepository.save(existing);
		return list();
	}

	public List<Todo> delete(Long id) {
		Todo todo = findById(id);
		validateOwnership(todo);
		todoRepository.deleteById(id);
		return list();
	}

}
