package com.myclass.repository;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myclass.entity.Course;

@Service
public interface CourseRepository {
	List<Course> getAll();
	boolean saveOrUpdate(Course course);
	boolean removeById(String id);
	Course findById(String id);
}
