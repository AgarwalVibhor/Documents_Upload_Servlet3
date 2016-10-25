package com.tcs.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.model.User;

@Repository("userDaoImpl")
public class UserDaoImpl implements UserDaoInterface {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional(readOnly = true)
	public User findById(Integer id) {
		
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("id", id));
		System.out.println("First Query fired for retrieving User.");
		User user = (User) cr.uniqueResult();
		t.commit();
		
		return user;
	}

	@Override
	@Transactional(readOnly = true)
	public User findBySso(String sso) {
		
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("ssoId", sso));
		System.out.println("First Query fired for retrieving User.");
		User user = (User) cr.uniqueResult();
		t.commit();
		
		return user;
	}

	@Override
	@Transactional(readOnly = false)
	public int save(User user) {
		
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		System.out.println("Both the queries for insert are fired simultaneously.");
		int pk = (Integer) session.save(user);
		t.commit();
		return pk;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteBySso(String sso) {
		
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("ssoId", sso));
		System.out.println("Query fired for retrieving User in delete.");
		User user = (User) cr.uniqueResult();
		System.out.println("Query fired for deleting User.");
		session.delete(user);
		t.commit();

	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<User> findAll() {
		
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		Criteria cr = session.createCriteria(User.class);
		cr.addOrder(Order.asc("firstName"));
		cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); // To avoid duplicates
		List<User> users = cr.list();
		t.commit();

		return users;
	}

	@Override
	@Transactional(readOnly = false)
	public void update(User user) {
		
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		session.update(user);
		t.commit();
		
	}


}
