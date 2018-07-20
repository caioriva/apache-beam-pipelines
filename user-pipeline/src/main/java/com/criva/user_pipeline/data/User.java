package com.criva.user_pipeline.data;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;

public class User {

	private String id;
	
	private String name;
	
	private Integer age;
	
	private String group;
	
	private Instant timestamp;

	public User() {
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getAge() {
		return age;
	}


	public void setAge(Integer age) {
		this.age = age;
	}


	public String getGroup() {
		return group;
	}


	public void setGroup(String group) {
		this.group = group;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}
}
