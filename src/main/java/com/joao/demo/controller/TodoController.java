package com.joao.demo.controller;

import com.joao.demo.dto.TodoRequestDTO;
import com.joao.demo.dto.TodoResponseDTO;
import com.joao.demo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
	private TodoService todoService;

	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	private String getUsername() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	@PostMapping
	public TodoResponseDTO create(@RequestBody @Valid TodoRequestDTO dto) {
		return todoService.create(dto, getUsername());
	}
	
	@GetMapping
	public List<TodoResponseDTO> list() {
		return todoService.list(getUsername());
	}

	@PutMapping("{id}")
	public TodoResponseDTO atualizarStatus(@PathVariable Long id,@RequestBody TodoRequestDTO dto){
		return todoService.update(id ,dto, getUsername());
	}
	
	@DeleteMapping("{id}")
	public List<TodoResponseDTO> delete(@PathVariable Long id) {
		return todoService.delete(id, getUsername());
	}
}
