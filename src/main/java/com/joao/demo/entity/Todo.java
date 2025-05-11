package com.joao.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "todos")
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String title;
	@NotBlank
	private String description;
	private Boolean done;
	private Integer priority;

	@ManyToOne
	private User user;

	public Todo() {
	}
	
	public Todo(String  title, String description, Boolean done, Integer priority) {
		this.title = title;
		this.description = description;
		this.done = done;
		this.priority = priority;
	}

	public @NotBlank String getTitle() {
		return title;
	}

	public void setTitle(@NotBlank String title) {
		this.title = title;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
