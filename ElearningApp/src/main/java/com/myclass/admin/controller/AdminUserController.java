package com.myclass.admin.controller;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.dto.UserDTO;
import com.myclass.dto.UserEditDTO;
import com.myclass.entity.User;
import com.myclass.repository.UserRepository;
import com.myclass.service.UserService;

@RestController
@RequestMapping("api/admin/user")
public class AdminUserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;

	@GetMapping("/all")
	@CrossOrigin("*")
	public Object getAll() {
		List<User> list = userRepository.findAll();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

	@PutMapping()
	public Object save(@RequestBody UserDTO userDTO) {
		try {
			if (userService.findByEmail(userDTO.getEmail())) {
				return new ResponseEntity<String>("Email đã tồn tại", HttpStatus.BAD_REQUEST);
			}
			//String hashed=BCrypt.hashpw(userDTO.getPassword(),BCrypt.gensalt());
			//userDTO.setPassword(hashed);
			boolean result = userService.save(userDTO);
			if (result)
				return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
			else
				return HttpStatus.BAD_REQUEST;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return HttpStatus.BAD_REQUEST;
	}
	
	@PutMapping("/update/{id}")
	public Object update(@PathVariable("id") String id,@RequestBody UserEditDTO userEditDTO) {
		if(userService.update(id, userEditDTO))
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		else {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/remove/{id}")
	public HttpStatus remove(@PathVariable("id") String id) {
		boolean result = userService.remove(id);
		if (result)
			return HttpStatus.OK;
		else
			return HttpStatus.BAD_REQUEST;

	}
}