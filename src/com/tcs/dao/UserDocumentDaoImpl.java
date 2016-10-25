package com.tcs.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.model.User;
import com.tcs.model.UserDocument;

@Repository("userDocumentDaoImpl")
public class UserDocumentDaoImpl implements UserDocumentDaoInterface {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional(readOnly = true)
	public UserDocument findById(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		Criteria cr = session.createCriteria(UserDocument.class);
		cr.add(Restrictions.eq("id", id));
		System.out.println("Query fired for retrieving UserDocument");
		UserDocument document = (UserDocument) cr.uniqueResult();
		t.commit();
		
		return document;
	}

	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<UserDocument> findAll() {
		
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		Criteria cr = session.createCriteria(UserDocument.class);
		System.out.println("Query fired for retrieving all user documents.");
		List<UserDocument> list = cr.list();
		t.commit();
		
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserDocument> findAllByUserId(Integer userId) {
		

		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("id", userId));
		System.out.println("First Query fired for retrieving User.");
		User user = (User) cr.uniqueResult();
		System.out.println("Second query fired for retrieving user documents.");
		Set<UserDocument> set = user.getUserDocuments();
		List<UserDocument> list = new ArrayList<UserDocument>(set);
		t.commit();
		
		return list;
	}

	@Override
	@Transactional(readOnly = false)
	public int save(UserDocument document) {
		System.out.println("Save document -- Dao");
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		System.out.println("Query fired for saving User Document.");
		int pk = (Integer) session.save(document);
		t.commit();
		return pk;

	}

	@Override
	@Transactional(readOnly = false)
	public void deleteById(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		UserDocument document = new UserDocument();
		document.setId(id);
		System.out.println("Query fired for deleting User Document.");
		session.delete(document);
		t.commit();

	}

}
