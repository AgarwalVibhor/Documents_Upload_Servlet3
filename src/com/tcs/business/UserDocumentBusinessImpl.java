package com.tcs.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.dao.UserDocumentDaoInterface;
import com.tcs.model.UserDocument;

@Service("userDocumentBusinessImpl")
public class UserDocumentBusinessImpl implements UserDocumentBusinessInterface {
	
	@Autowired
	@Qualifier("userDocumentDaoImpl")
	private UserDocumentDaoInterface userDocumentDao;

	@Override
	@Transactional(readOnly = true)
	public UserDocument findById(int id) {
		
		return userDocumentDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserDocument> findAll() {
		
		return userDocumentDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserDocument> findAllByUserId(Integer userId) {
		
		return userDocumentDao.findAllByUserId(userId);
	}

	@Override
	@Transactional(readOnly = false)
	public int save(UserDocument document) {
		System.out.println("Save document -- Business");
		int pk = userDocumentDao.save(document);
		return pk;

	}

	@Override
	@Transactional(readOnly = false)
	public void deleteById(int id) {
		
		userDocumentDao.deleteById(id);

	}

}
