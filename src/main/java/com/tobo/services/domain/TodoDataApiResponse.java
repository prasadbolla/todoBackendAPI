/**
 * 
 */
package com.tobo.services.domain;

import java.util.List;

import com.tobo.services.model.Todo;

/**
 * @author PRASADBolla
 *
 */
public class TodoDataApiResponse {
	public List<Todo> todos;

	/**
	 * @return the todos
	 */
	public List<Todo> getTodos() {
		return todos;
	}

	/**
	 * @param todos the todos to set
	 */
	public void setTodos(List<Todo> todos) {
		this.todos = todos;
	}
	
}
