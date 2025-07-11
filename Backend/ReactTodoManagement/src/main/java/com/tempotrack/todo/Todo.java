package com.tempotrack.todo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
	
	private int id;
	private String username;
	private String description;
	private boolean isDone;

}
