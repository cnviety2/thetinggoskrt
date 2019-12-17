package com.myclass.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Course;
import com.myclass.repository.CourseRepository;

@Repository
@Transactional(rollbackOn = Exception.class)
public class CourseRepositoryImpl implements CourseRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Course> getAll() {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Course> query = session.createQuery("FROM Course", Course.class);
			List<Course> list = query.getResultList();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Course>();
	}

	public boolean saveOrUpdate(Course course) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(course);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean removeById(String id) {
		Course course = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			course = session.find(Course.class, id);
			if (course == null)
				return false;
			else {
				session.remove(course);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Course findById(String id) {
		Course course = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			course = session.find(Course.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return course;
	}

}
