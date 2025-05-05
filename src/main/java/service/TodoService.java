package service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.joao.demo.entity.Todo;
import com.joao.demo.repository.TodoRepository;

@Service
public class TodoService {
	
	private TodoRepository todoRepository;
	
	public TodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	public List<Todo> create(Todo todo) {
		todoRepository.save(todo);
		return list();
	}

	public List<Todo> list() {
		//Ordenando a consulta por prioridade e logo em seguida por nome
		Sort sort = Sort.by("prioridade").descending().and(Sort.by("nome").ascending());
		return todoRepository.findAll(sort);
	}

	public List<Todo> update(Todo todo) {
		todoRepository.save(todo);
		return list();
	}

	public List<Todo> delete(Long id) {
		todoRepository.deleteById(id);;
		return list();
	}

}
