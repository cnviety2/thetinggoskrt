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

import com.myclass.entity.Category;
import com.myclass.repository.CategoryRepository;

@RestController
@RequestMapping("api/admin/category")
public class AdminCategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping("/all")
	public Object findAll() { 
		List<Category> list=categoryRepository.getAll();
		return new ResponseEntity<List<Category>>(list,HttpStatus.OK);
	}
	
	@PutMapping("/edit")
	public HttpStatus edit(@Valid @RequestBody Category category) {
		boolean result=categoryRepository.saveOrUpdate(category);
		if(result)
			return HttpStatus.OK;
		else
			return HttpStatus.BAD_REQUEST;
	}
	
	@DeleteMapping("/remove/{id}")
	public HttpStatus remove(@PathVariable("id") String id) {
		boolean result=categoryRepository.removeById(id);
		if(result)
			return HttpStatus.OK;
		else
			return HttpStatus.BAD_REQUEST;
	}
	
	@GetMapping("/find/{id}")
	public Object getCategoryById(@PathVariable("id") String id) {
		Category category=categoryRepository.findById(id);
		if(category == null)
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<Category>(category,HttpStatus.OK);
	}
	
}
