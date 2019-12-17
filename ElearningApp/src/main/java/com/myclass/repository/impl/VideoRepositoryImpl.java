package com.myclass.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Video;
import com.myclass.repository.VideoRepository;

@Repository
@Transactional(rollbackOn = Exception.class)
public class VideoRepositoryImpl implements VideoRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Video> getAll() {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Video> query = session.createQuery("FROM Video", Video.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Video>();
	}

	public boolean saveOrUpdate(Video video) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(video);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean remove(String id) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Video video = session.find(Video.class, id);
			if (video == null)
				return false;
			else {
				session.remove(video);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Video getById(String id) {
		Video video = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			video = session.find(Video.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return video;

	}

}
