package com.myclass.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Category;
import com.myclass.repository.CategoryRepository;

@Repository
@Transactional(rollbackOn = Exception.class)
public class CategoryRepositoryImpl implements CategoryRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Category> getAll() {
		List<Category> list;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Category> query = session.createQuery("FROM Category", Category.class);
			list = query.getResultList();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Category>();
	}

	public boolean saveOrUpdate(Category category) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean removeById(String id) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Category category = session.find(Category.class, id);
			if (category == null)
				return false;
			else {
				session.remove(category);
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Category findById(String id) {
		Category category = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			category = session.find(Category.class, id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return category;
	}

}
