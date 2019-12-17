package com.myclass.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.myclass.entity.Role;

@Controller
public class RoleController {
	private List<Role> list;

	@PostConstruct
	private void init() {
		list = new ArrayList<Role>();
		list.add(new Role("1","duy1","sssssdd"));
		list.add(new Role("2","duy2","asssddd"));
		list.add(new Role("3","duy3","assssdddavuibgvfuib"));
	}

	/*@RequestMapping(value = "role/add", method = RequestMethod.POST)
	@ResponseBody
	public List<Role> add(@RequestParam(value = "name", defaultValue = "duy") String name,
			@RequestParam(value = "description", defaultValue = "nothing") String description,
			BindingResult errors) {
		if(name == null || name.length() <= 4 || name.isEmpty()) {
			
		}
		
		if(description == null || description.isEmpty()) {
			
		}
		
		list.add(new Role("ssssss", name, description));
		return list;
	}*/
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Role> add(@Valid @RequestBody Role role,
			BindingResult errors) {
		if(errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
		}
		
		role.setId(UUID.randomUUID().toString());
		list.add(role);
		return new ResponseEntity(list,HttpStatus.OK);
	}

	@RequestMapping(value = "role", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Role>> getAll() {
		if (list.size() == 0) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity(list, HttpStatus.OK);
	}

	@RequestMapping(value = "role/search", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Role> search(@RequestParam(value = "name") String name) {
		for (int i = 0; i < list.size(); i++) {
			Role temp = list.get(i);
			if (name.equals(temp.getName())) {
				return new ResponseEntity(temp, HttpStatus.OK);
			}
		}
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "role/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Role> remove(@RequestParam(value = "name") String name) {
		for (int i = 0; i < list.size(); i++) {
			Role temp = list.get(i);
			if (name.equals(temp.getName())) {
				list.remove(i);
				return new ResponseEntity(temp, HttpStatus.OK);
			}
		}
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

}
