package com.myclass.repository;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myclass.entity.User;

@Service
public interface UserRepository {
	List<User> findAll();
	User findByID(String id);
	User findByEmail(String email);
	boolean saveOrUpdate(User user);
	boolean remove(User user);
	boolean removeByID(String id);
}
