package com.tcs.model;

import org.springframework.web.multipart.MultipartFile;

public class FileBucket {
	
	
	MultipartFile multipartFile;
	/*
	 * This is the representation of an uploaded file received in a multipart request. It provides handy methods like
	 * getName(), getSize(), getContentType(), getBytes() etc.
	 */
	
	String description;

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
