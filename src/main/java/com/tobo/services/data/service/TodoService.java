/**
 * 
 */
package com.tobo.services.data.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tobo.services.model.Todo;
import com.tobo.services.util.CommonUtil;

/**
 * @author PRASADBolla
 *
 */
@Service
public class TodoService {
	private static List<Todo> todoInfoList = new ArrayList();


	/**
	 * @return
	 */
	public List<Todo> getTodoInfoList() {
		return todoInfoList;
	}
	/**
	 * @param id
	 * @return
	 */
	public Todo getTodoById(long id) {
		if(CommonUtil.isEmptyList(todoInfoList))
			return null;

		return todoInfoList.stream()
				.filter(todoItem -> todoItem.getId() == id).findFirst().get();
	}
	/**
	 * @param id
	 * @return
	 */
	public boolean removeTodoByid(long id) {
		return (todoInfoList.removeIf(todo -> todo.getId() == id));
	}

	/**
	 * This method with will update the status based on data available in DB.
	 * 
	 * @param todo
	 * @return
	 */
	public Todo updateTodo(Todo todo) {
		if (todo != null && !CommonUtil.isNullOrEmpty(todo.getStatus())) {
			if (todo.getStatus().equalsIgnoreCase(CommonUtil.NO)) {
				todo.setStatus(CommonUtil.YES);
				todo.setChecked(true);
			}else{
				todo.setStatus(CommonUtil.NO);
				todo.setChecked(false);
			}
		}
		return todo;
	}
}
