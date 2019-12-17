package com.myclass.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Role;
import com.myclass.repository.RoleRepository;

@Repository
@Transactional(rollbackOn = Exception.class)
public class RoleRepositoryImpl implements RoleRepository {

	@Autowired
	private SessionFactory sessionFactory; // NGân hàng

	public List<Role> getAll() {
		// Tạo đối tượng Session để truy vấn database
		Session session = sessionFactory.getCurrentSession(); // Nhân viên ngân hàng
		try {
			// Thực hiện giao dịch
			Query<Role> query = session.createQuery("FROM Role", Role.class);
			List<Role> roles = query.getResultList();
			return roles;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Role>();
	}

	public boolean saveOrUpdate(Role role) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.saveOrUpdate(role);
			return true;

		} catch (Exception er) {
			er.printStackTrace();
		}
		return false;
	}

	public boolean removeByID(String id) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Role entity = session.find(Role.class, id);
			if (entity == null) {
				return false;
			}
			session.delete(entity);
			return true;
		} catch (Exception er) {
			er.printStackTrace();
			return false;
		}
	}

	public Role findById(String id) {
		Session session =sessionFactory.getCurrentSession();
		Role role=null;
		try {
			role=session.find(Role.class, id);
			return role;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return role;
	}
}
