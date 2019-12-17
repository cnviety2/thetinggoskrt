package com.myclass.entity;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class Role {
	private String id;

	@NotBlank(message = "Nhập tên")
	@Length(min = 4, message = "Tên ít nhất 4 ký tự")
	private String name;

	@NotBlank(message = "Nhập mô tả")
	private String description;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Role(String id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Role() {
	}
}
