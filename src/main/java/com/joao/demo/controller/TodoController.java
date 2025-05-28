package com.joao.demo.controller;

import com.joao.demo.entity.Todo;
import com.joao.demo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
	private TodoService todoService;
	
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	@PostMapping
	public List<Todo> create(@RequestBody @Valid Todo todo) {
		return todoService.create(todo);
	}

	@GetMapping("{id}")
	public Todo findById(@PathVariable Long id){
		return todoService.findById(id);
	}
	
	@GetMapping
	public List<Todo> list() {
		return todoService.list();
	}

	@PutMapping("{id}")
	public List<Todo> atualizarStatus(@PathVariable Long id, @RequestBody Todo todo){
		todo.setId(id);
		return todoService.update(todo);
	}
	
	@DeleteMapping("{id}")
	public List<Todo> delete(@PathVariable Long id) {
		return todoService.delete(id);
	}
}
