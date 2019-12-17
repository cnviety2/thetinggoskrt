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

import com.myclass.entity.Result;
import com.myclass.repository.ResultRepository;

@RestController
@RequestMapping("api/admin/result")
public class AdminResultController {

	@Autowired
	private ResultRepository resultRepository;

	@GetMapping("/all")
	public Object findAll() {
		List<Result> list = resultRepository.getAll();
		return new ResponseEntity<List<Result>>(list, HttpStatus.OK);
	}

	@PutMapping("/edit")
	public HttpStatus edit(@Valid @RequestBody Result rs) {
		boolean result = resultRepository.saveOrUpdate(rs);
		if (result)
			return HttpStatus.OK;
		else
			return HttpStatus.BAD_REQUEST;
	}

	@DeleteMapping("/remove/{id}")
	public HttpStatus remove(@PathVariable("id") String id) {
		boolean result = resultRepository.removeById(id);
		if (result)
			return HttpStatus.OK;
		else
			return HttpStatus.BAD_REQUEST;

	}

	@GetMapping("/find/{id}")
	public Object getResultById(@PathVariable("id") String id) {
		Result result = resultRepository.getById(id);
		if (result == null)
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<Result>(result, HttpStatus.OK);
	}

}
