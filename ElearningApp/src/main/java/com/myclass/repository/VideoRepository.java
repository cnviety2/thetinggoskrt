package com.myclass.repository;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myclass.entity.Video;

@Service
public interface VideoRepository {
	List<Video> getAll();

	boolean saveOrUpdate(Video video);

	boolean remove(String id);

	Video getById(String id);
}
