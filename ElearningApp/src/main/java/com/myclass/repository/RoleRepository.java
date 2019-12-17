package com.myclass.repository;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myclass.entity.Role;

@Service
public interface RoleRepository {
	List<Role> getAll();

	boolean saveOrUpdate(Role role);
	Role findById(String id);
	boolean removeByID(String id);
}
