package com.criva.pub_sub_publisher.data;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;

public class User {

	private String id;
	
	private String name;
	
	private Integer age;
	
	private String group;
	
	private String timestamp;

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

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public static User getRandomUser() {
		
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setName(new NameGenerator().getRandomName());
		user.setAge(new Random().nextInt(100));
		user.setGroup(new GroupGenerator().getRandomGroup());
		user.setTimestamp(Instant.now().toString());
		
		return user;
	}
}
