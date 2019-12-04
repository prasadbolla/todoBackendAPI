/**
 * 
 */
package com.tobo.services.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tobo.services.dao.TodoRepository;
import com.tobo.services.data.service.TodoService;
import com.tobo.services.domain.TodoDataApiResponse;
import com.tobo.services.model.Todo;

/**
 * @author PRASADBolla 
 * 
 * Added TodoDataApiController to support create, edit,
 *         delete, and view functionality.
 *
 */
@CrossOrigin
@RestController
public class TodoDataApiController {
	@Autowired
	private TodoService todoInfoService;

	@Autowired
	private TodoRepository todoRepository;

	/**
	 * @param username
	 * @param todo
	 * @return
	 */
	@PostMapping("/users/{username}/todos/todo")
	public ResponseEntity<Void> createTodo(@PathVariable String username,
			@RequestBody Todo todo) {
		Todo newResource = todoRepository.save(todo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(newResource.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	/**
	 * @param username
	 * @param id
	 * @param todo
	 * @return
	 */
	@PutMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username,
			@PathVariable long id, @RequestBody Todo todo) {
		Todo updatedTodo = todoRepository.save(todo);
		return ResponseEntity.ok(updatedTodo);
	}
	
	@GetMapping(value = "/users/{username}/todos")
	public TodoDataApiResponse getTodoInfoList(@PathVariable String username) {
		TodoDataApiResponse dataResponse = new TodoDataApiResponse();
		dataResponse.setTodos(todoRepository.findByUsername(username));
		return dataResponse;
	}

	/**	
	 * @param username
	 * @param id
	 * @return
	 */
	@GetMapping("/users/{username}/todos/{id}")
	public Todo getTodoById(@PathVariable String username, @PathVariable long id) {
		return todoRepository.findById(id).get();
	}

	/**
	 * @param username
	 * @param id
	 * @param todo
	 * @return
	 */
	@PatchMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Todo> patchTodo(@PathVariable String username,
			@PathVariable long id, @RequestBody Todo todo) {
		
		Todo updatedTodo = todoRepository.save(todoInfoService
				.updateTodo(todo));

		return ResponseEntity.ok(updatedTodo);
	}
	
	/**
	 * @param username
	 * @param id
	 * @return
	 */
	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String username,
			@PathVariable long id) {
		todoRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}
}
