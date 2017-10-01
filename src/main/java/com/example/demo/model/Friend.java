package com.example.demo.model;

import lombok.Data;

@Data
public class Friend {
	
	Long id;
	
	String name;
	
	public Friend(Long id, String name) {
		this.id = id;
		this.name = name;
	}

}
