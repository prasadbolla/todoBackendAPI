/**
 * 
 */
package com.tobo.services.domain;

import java.util.List;

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
