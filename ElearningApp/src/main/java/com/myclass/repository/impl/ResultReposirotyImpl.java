package com.myclass.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Result;
import com.myclass.repository.ResultRepository;

@Repository
@Transactional(rollbackOn = Exception.class)
public class ResultReposirotyImpl implements ResultRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Result> getAll() {
		Session session = sessionFactory.getCurrentSession();
		try {
			Query<Result> query = session.createQuery("FROM Result", Result.class);
			List<Result> list = query.getResultList();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Result>();

	}

	public boolean saveOrUpdate(Result result) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(result);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean removeById(String id) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Result result = session.find(Result.class, id);
			if (result == null)
				return false;
			else {
				session.remove(result);
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Result getById(String id) {
		Result result = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			result = session.find(Result.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

}
