package com.myclass.repository;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myclass.entity.Result;

@Service
public interface ResultRepository {
	List<Result> getAll();
	boolean saveOrUpdate(Result result);
	boolean removeById(String id);
	Result getById(String id);
}
