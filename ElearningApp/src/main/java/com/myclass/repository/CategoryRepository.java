package com.myclass.repository;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myclass.entity.Category;

@Service
public interface CategoryRepository {
	List<Category> getAll();
	boolean saveOrUpdate(Category category);
	boolean removeById(String id);
	Category findById(String id);
}
