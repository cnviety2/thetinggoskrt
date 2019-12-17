package com.myclass.admin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.entity.Role;
import com.myclass.repository.RoleRepository;

@RestController
@RequestMapping("api/admin/role")
public class AdminRoleController {

	@Autowired
	private RoleRepository roleRepository;

	@GetMapping("/all")
	public Object getAll() {
		List<Role> roles = roleRepository.getAll();
		return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
	}

	@PutMapping("/edit")
	public Object edit(@RequestBody Role role) {
		boolean result = roleRepository.saveOrUpdate(role);
		if (result) {
			return new ResponseEntity<Role>(role, HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);

	}

	@DeleteMapping("remove/{id}")
	public HttpStatus remove(@PathVariable("id") String id) {
		boolean result = roleRepository.removeByID(id);
		if (result) {
			return HttpStatus.OK;
		}
		return HttpStatus.BAD_REQUEST;
	}
	
	@GetMapping("find/{id}")
	public Object getRoleById(@PathVariable("id") String id) {
		Role role=roleRepository.findById(id);
		if(role==null)
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<Role>(role,HttpStatus.OK);
	}
}
