package com.tcs.business;

import java.util.List;

import com.tcs.model.User;

public interface UserBusinessInterface {
	
	public User findById(Integer id);
	
	public User findBySso(String sso);
	
	public int save(User user);
	
	public void update(User user);
	
	public void deleteBySso(String sso);
	
	public List<User> findAll();

}
