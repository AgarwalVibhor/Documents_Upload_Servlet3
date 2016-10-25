package com.tcs.business;

import java.util.List;

import com.tcs.model.UserDocument;

public interface UserDocumentBusinessInterface {
	
	public UserDocument findById(int id);
	
	public List<UserDocument> findAll();
	
	public List<UserDocument> findAllByUserId(Integer userId);
	
	public int save(UserDocument document);
	
	public void deleteById(int id);

}
