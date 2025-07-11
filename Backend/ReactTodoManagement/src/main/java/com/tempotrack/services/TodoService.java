package com.tempotrack.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tempotrack.todo.Todo;

@Service
public class TodoService {
	
	private static ArrayList<Todo> todos = new ArrayList<>();
	
	private static int statusCount = 0;
	
	static {
		todos.add(new Todo(statusCount++, "kam", "udemy course", false));
		todos.add(new Todo(statusCount++, "kam", "Milestone 1", true));
		todos.add(new Todo(statusCount++, "kam", "Milestone 2", false));
		todos.add(new Todo(statusCount++, "kam", "Milestone 3", false));
		
		todos.add(new Todo(statusCount++, "admin", "this course", false));
		todos.add(new Todo(statusCount++, "admin", "React", true));
	}
	
	public List<Todo> findAllByUsername(String username){
		return todos.stream().filter((todo) -> todo.getUsername().equals(username)).toList();
	}
	
	public void addTodo(Todo todo) {
		todos.add(todo);
	}
	
	public void deleteById(int id) {
		todos.removeIf(todo -> todo.getId() == id);
	}
	
	public Todo findById(int id) {
		return todos.stream().filter(todo -> todo.getId() == id).findFirst().get();
	}
	
	public void updateById(Todo todo) {
		deleteById(todo.getId());
		addTodo(todo);
	}

}
