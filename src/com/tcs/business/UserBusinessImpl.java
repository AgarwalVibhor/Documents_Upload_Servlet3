package com.tcs.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.dao.UserDaoInterface;
import com.tcs.model.User;

@Service("userBusinessImpl")
public class UserBusinessImpl implements UserBusinessInterface {
	
	@Autowired
	@Qualifier("userDaoImpl")
	private UserDaoInterface userDao;

	@Override
	@Transactional(readOnly = true)
	public User findById(Integer id) {
		
		return userDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public User findBySso(String sso) {
		
		return userDao.findBySso(sso);
	}

	@Override
	@Transactional(readOnly = false)
	public int save(User user) {
		
		int pk = userDao.save(user);
		return pk;

	}

	@Override
	@Transactional(readOnly = false)
	public void update(User user) {
		
		userDao.update(user);

	}

	@Override
	@Transactional(readOnly = false)
	public void deleteBySso(String sso) {
		
		userDao.deleteBySso(sso);

	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		
		return userDao.findAll();
	}

}
