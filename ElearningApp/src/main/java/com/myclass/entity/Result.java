package com.myclass.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "results")
public class Result {
	@Id
	private String id;
	@NotBlank(message = "Not blank")
	private String title;
	
	@Column(name = "course_id")
	private String courseID;
	
	@ManyToOne
	@JoinColumn(name = "course_id",insertable = false, updatable = false)
	private Course course;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public Result(String id, String title, String courseID) {
		super();
		this.id = id;
		this.title = title;
		this.courseID = courseID;
	}

	public Result() {
	}

}
