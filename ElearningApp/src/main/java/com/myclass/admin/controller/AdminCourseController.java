package com.myclass.admin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.entity.Course;
import com.myclass.repository.CourseRepository;

@RestController
@RequestMapping("api/admin/course")
public class AdminCourseController {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@GetMapping("/all")
	public Object findAll() {
		List<Course> list=courseRepository.getAll();
		return new ResponseEntity<List<Course>>(list,HttpStatus.OK);
		
	}
	
	@PutMapping("/edit")
	public HttpStatus edit(@Valid @RequestBody Course course) {
		boolean result=courseRepository.saveOrUpdate(course);
		if(result)
			return HttpStatus.OK;
		else
			return HttpStatus.BAD_REQUEST;
	}
	
	@DeleteMapping("/remove/{id}")
	public HttpStatus remove(@PathVariable("id") String id) {
		boolean result=courseRepository.removeById(id);
		if(result)
			return HttpStatus.OK;
		else 
			return HttpStatus.BAD_REQUEST;
	}
	
	@GetMapping("/find/{id}")
	public Object getCourseById(@PathVariable("id") String id) {
		Course course=courseRepository.findById(id);
		if(course == null)
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<Course>(course,HttpStatus.OK);
	}

}
