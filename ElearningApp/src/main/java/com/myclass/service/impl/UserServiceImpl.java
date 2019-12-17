package com.myclass.service.impl;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.dto.UserDTO;
import com.myclass.dto.UserEditDTO;
import com.myclass.entity.User;
import com.myclass.repository.UserRepository;
import com.myclass.service.UserService;
import com.sun.corba.se.impl.encoding.CodeSetConversion.BTCConverter;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAll() {
		return userRepository.findAll();

	}

	public boolean save(UserDTO userDTO) {
		String hashed=BCrypt.hashpw(userDTO.getPassword(),BCrypt.gensalt());
		User user = new User(userDTO.getId(), userDTO.getEmail(), userDTO.getFullname(), hashed,
				userDTO.getAvatar(), userDTO.getPhone(), userDTO.getAddress(), userDTO.getWebsite(),
				userDTO.getFacebook(), userDTO.getRoleID());
		try {
			userRepository.saveOrUpdate(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean findByEmail(String email) {
		if (userRepository.findByEmail(email) != null)
			return true;
		else
			return false;

	}

	public boolean update(String id,UserEditDTO userEditDTO) {
		User user=userRepository.findByID(id);
		if(user==null)
			return false;
		else {
			user.setAddress(userEditDTO.getAddress());
			user.setAvatar(userEditDTO.getAvatar());
			user.setFacebook(userEditDTO.getFacebook());
			user.setFullname(userEditDTO.getFullname());
			user.setPhone(userEditDTO.getPhone());
			user.setWebsite(userEditDTO.getWebsite());
			userRepository.saveOrUpdate(user);
			return true;
		}
	}

	public boolean remove(String id) {
		try {
			userRepository.removeByID(id);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
