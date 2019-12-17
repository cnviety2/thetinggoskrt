package com.myclass.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "courses")
public class Course {
	@Id
	private String id;
	@NotBlank(message = "Not blank")
	private String title;
	private String image;
	@Column(name = "lectures_count")
	// @NotBlank(message = "Not blank")
	private int lecturesCount;
	@Column(name = "hour_count")
	private int hourCount;
	// @NotBlank(message = "Not blank")
	private double price;
	private String description;

	@Column(name = "category_id")
	private String categoryID;

	@ManyToOne
	@JoinColumn(name = "category_id", insertable = false, updatable = false)
	private Category category;

	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
	private List<Result> results;

	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
	private List<Video> videos;

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getLecturesCount() {
		return lecturesCount;
	}

	public void setLecturesCount(int lecturesCount) {
		this.lecturesCount = lecturesCount;
	}

	public int getHourCount() {
		return hourCount;
	}

	public void setHourCount(int hourCount) {
		this.hourCount = hourCount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}

	public Course(String id, String title, String image, int lecturesCount, int hourCount, double price,
			String description, String categoryID) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
		this.lecturesCount = lecturesCount;
		this.hourCount = hourCount;
		this.price = price;
		this.description = description;
		this.categoryID = categoryID;
	}

	public Course() {
	}

}
