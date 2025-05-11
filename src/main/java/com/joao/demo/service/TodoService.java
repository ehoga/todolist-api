package com.joao.demo.service;

import com.joao.demo.entity.Todo;
import com.joao.demo.repository.TodoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
	
	private TodoRepository todoRepository;
	
	public TodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	public List<Todo> create(Todo todo) {
		if (todo.getPriority() == null){
			Integer maxPriority = todoRepository.findMaxPriority();
			todo.setPriority((maxPriority != null ? maxPriority : 0) + 1);
		}
		todoRepository.save(todo);
		return list();
	}

	public Todo findById(Long id){
		return todoRepository.findById(id).orElse(null);
	}

	public List<Todo> list() {
		Sort sort = Sort.by("priority").ascending().and(Sort.by("title").ascending());
		return todoRepository.findAll(sort);
	}

	public List<Todo> update(Todo todo) {
		Optional<Todo> existingTodoOpt = todoRepository.findById(todo.getId());

		if (existingTodoOpt.isPresent()){
			Todo existing = existingTodoOpt.get();

			if(todo.getTitle() != null) existing.setTitle(todo.getTitle());
			if(todo.getDescription() != null) existing.setDescription(todo.getDescription());
			if(todo.getDone() != null) existing.setDone(todo.getDone());
			if(todo.getPriority() != null) existing.setPriority(todo.getPriority());

			todoRepository.save(existing);
		}

		return list();
	}

	public List<Todo> delete(Long id) {
		todoRepository.deleteById(id);;
		return list();
	}

}
