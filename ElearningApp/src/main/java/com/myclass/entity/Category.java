package com.myclass.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name="categories")
public class Category {
	@Id
	private String id;
	@NotBlank(message = "Not null")
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@NotBlank(message = "Not null")
	private String icon;
	
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private List<Course> courses;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Category(String id, String title, String icon) {
		super();
		this.id = id;
		this.title = title;
		this.icon = icon;
	}
	
	public Category() {}
}
