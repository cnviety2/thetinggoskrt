package com.myclass.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myclass.entity.User;
import com.myclass.repository.UserRepository;

@Repository
@Transactional(rollbackOn = Exception.class)
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public List<User> findAll() {
		Session session = sessionFactory.getCurrentSession();
		try {
			Query<User> query = session.createQuery("FROM User", User.class);
			List<User> users = query.getResultList();
			return users;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<User>();
	}

	public User findByID(String id) {
		Session session = sessionFactory.getCurrentSession();
		try {
			User user = session.find(User.class, id);
			if (user == null)
				return null;
			else
				return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public User findByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "FROM User where email=:email";
			Query<User> query = session.createQuery(hql, User.class);
			query.setParameter("email", email);
			User user = query.getSingleResult();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean saveOrUpdate(User user) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.saveOrUpdate(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean remove(User user) {
		Session session = sessionFactory.getCurrentSession();
		try {
			User temp = session.find(User.class, user.getId());
			if (temp == null) {
				return false;
			}
			session.remove(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean removeByID(String id) {
		Session session=sessionFactory.getCurrentSession();
		try {
			User user=session.load(User.class, id);
			session.remove(user);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
