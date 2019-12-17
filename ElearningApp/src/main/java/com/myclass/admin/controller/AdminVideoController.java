package com.myclass.admin.controller;

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

import com.myclass.entity.Video;
import com.myclass.repository.VideoRepository;

@RestController
@RequestMapping("api/admin/video")
public class AdminVideoController {

	@Autowired
	private VideoRepository videoRepository;

	@GetMapping("/all")
	public Object getAll() {
		return videoRepository.getAll();
	}

	@PutMapping("/edit")
	public HttpStatus edit(@RequestBody Video video) {
		boolean result = videoRepository.saveOrUpdate(video);
		if (result)
			return HttpStatus.OK;
		else
			return HttpStatus.BAD_REQUEST;
	}

	@DeleteMapping("/remove/{id}")
	public HttpStatus remove(@PathVariable("id") String id) {
		boolean result = videoRepository.remove(id);
		if (result)
			return HttpStatus.OK;
		else
			return HttpStatus.BAD_REQUEST;
	}

	@GetMapping("/find/{id}")
	public Object getVideoById(@PathVariable("id") String id) {
		Video video = videoRepository.getById(id);
		if (video == null)
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<Video>(video, HttpStatus.OK);
	}

}
