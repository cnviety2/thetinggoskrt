package com.myclass.service;

import java.util.List;

import com.myclass.dto.UserDTO;
import com.myclass.dto.UserEditDTO;
import com.myclass.entity.User;

public interface UserService {
	List<User> getAll();
	boolean findByEmail(String email);
	boolean save(UserDTO userDTO);
	boolean update(String id,UserEditDTO userEditDTO);
	boolean remove(String id);
}
